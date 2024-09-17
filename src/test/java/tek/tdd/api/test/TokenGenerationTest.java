package tek.tdd.api.test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.EndPoints;

import java.util.HashMap;
import java.util.Map;

public class TokenGenerationTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(TokenGenerationTest.class);

    public Map<String, String> requestBodyUsers(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be null or empty");
        }
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        return requestBody;
    }

    @Test(dataProvider = "credentials")
    public void generateTokenWithSupervisor(String username, String password) {
        RequestSpecification request = getDefaultRequest();
        request.body(requestBodyUsers(username, password));
        Response response = request.when().post(EndPoints.GENERATE_TOKEN.getValue());
        response.then().statusCode(200);
        LOGGER.info("Response is {} ", response.prettyPrint());

        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, username, "Username should match");

        String token = response.body().jsonPath().getString("token");
        Assert.assertNotNull(token);

        String actualAccountType =  response.body().jsonPath().getString("accountType");
        Assert.assertEquals(actualAccountType,"CSR","account type should match");

    }

    @Test(dataProvider = "negativeCredentials")
    public void negativeGenerateToken(String username,String password,int status,String expectedErrorMessage){
        RequestSpecification request = getDefaultRequest();
        request.body(requestBodyUsers(username, password));
        Response response = request.when().post(EndPoints.GENERATE_TOKEN.getValue());
        response.then().statusCode(status);
        LOGGER.info("Response is {} ", response.prettyPrint());

        String actualErrorMessage = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should match");



    }

    @DataProvider
    private Object[][] negativeCredentials(){
        return new Object[][]{
                {"supervisor1", "tek_supervisor",404,"User supervisor1 not found"},
                {"supervisor", "tek_super",400,"Password not matched"},
        };
    }

    @DataProvider
    private String[][] credentials() {
        return new String[][]{
                {"supervisor", "tek_supervisor"},
                {"operator_readonly", "Tek4u2024"}
        };
    }
}
