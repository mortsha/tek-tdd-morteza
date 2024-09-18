package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountType;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.TokenResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.api.models.EndPoints;

public class TokenGenerationTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(TokenGenerationTest.class);

    @Test(dataProvider = "positiveCredentials")
    public void generateTokenWithSupervisor(String username, String password) {
        RequestSpecification request = getDefaultRequest();
        request.body(getTokenRequestBody(username, password));
        Response response = request.when().post(EndPoints.POST_GENERATE_TOKEN.getValue());
        response.then().statusCode(200);
        LOGGER.info("Response is {} ", response.prettyPrint());

        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, username, "Username should match");

        String token = response.body().jsonPath().getString("token");
        Assert.assertNotNull(token);

        String actualAccountType = response.body().jsonPath().getString("accountType");
        Assert.assertEquals(actualAccountType, "CSR", "account type should match");
        ExtentTestManager.getTest().info("Generate token with " + username + " and " + password);
    }

    @Test(dataProvider = "negativeCredentials")
    public void negativeGenerateToken(String username, String password, int status, String expectedErrorMessage, String httpStatus) {
        RequestSpecification request = getDefaultRequest();
        request.body(getTokenRequestBody(username, password));
        Response response = request.when().post(EndPoints.POST_GENERATE_TOKEN.getValue());
        response.then().statusCode(status);
        LOGGER.info("Response is {} ", response.prettyPrint());

        String actualErrorMessage = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should match");
        String actualStatus = response.jsonPath().getString("httpStatus");
        Assert.assertEquals(actualStatus, httpStatus, "https status should match");
        ExtentTestManager.getTest().info("Generate invalid token with " + username + " and " + password + " and validating errors " + expectedErrorMessage + " with status " + status);
    }

    @DataProvider
    private Object[][] negativeCredentials() {
        return new Object[][]{
                {"supervisor1", "tek_supervisor", 404, "User supervisor1 not found", "NOT_FOUND"},
                {"supervisor", "tek_super", 400, "Password not matched", "BAD_REQUEST"},
        };
    }

    @DataProvider
    private String[][] positiveCredentials() {
        return new String[][]{
                {"supervisor", "tek_supervisor"},
                {"operator_readonly", "Tek4u2024"}
        };
    }

    @Test
    public void generateTokenUseObjectAsBody() {
        RequestSpecification request = getDefaultRequest();
        TokenRequest requestBody = new TokenRequest("supervisor", "tek_supervisor");
        request.body(requestBody);
        Response response = request.when().post(EndPoints.POST_GENERATE_TOKEN.getValue());
        response.then().statusCode(200);
        response.prettyPrint();
        ExtentTestManager.getTest().info("Generate token with " + requestBody.getUsername() + " and " + requestBody.getPassword());
    }

    @Test
    public void convertResponseToPOJO() {
        TokenRequest request = new TokenRequest("supervisor", "tek_supervisor");

        Response response = getDefaultRequest().body(request).when().post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response();

        extentInfo(response.asPrettyString());

        TokenResponse token = response.body().jsonPath().getObject("", TokenResponse.class);

        Assert.assertEquals(token.getUsername(), "supervisor");
        Assert.assertNotNull(token.getToken());
        Assert.assertEquals(token.getAccountType(), AccountType.CSR);

    }



}
