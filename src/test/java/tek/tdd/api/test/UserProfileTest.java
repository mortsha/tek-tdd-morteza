package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.api.models.request.TokenRequest;
import tek.tdd.api.models.response.TokenResponse;
import tek.tdd.base.ApiTestsBase;

public class UserProfileTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(UserProfileTest.class);

    @Test(dataProvider = "positiveCredentials")
    public void getUserProfile(String username, String password, String expectedFullName, String expectedUsername) {
        TokenRequest request = new TokenRequest(username, password);

        Response responseToken = getDefaultRequest().body(request).when().post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response();
        LOGGER.info("Getting token with user {} and response {} ", username, responseToken.prettyPrint());
        extentInfo(responseToken.asPrettyString());

        TokenResponse token = responseToken.body().jsonPath().getObject("", TokenResponse.class);
        String tokenGenerated = "Bearer " + token.getToken();
        Response response = getDefaultRequest().header("Authorization", tokenGenerated)
                .when()
                .get(EndPoints.GET_PROFILE.getValue())
                .then().statusCode(200).extract().response();

        response.prettyPrint();
        String actualFullName = response.body().jsonPath().getString("fullName");
        Assert.assertEquals(actualFullName, expectedFullName);
        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, expectedUsername);
    }

    @DataProvider
    private String[][] positiveCredentials() {
        return new String[][]{
                {"supervisor", "tek_supervisor", "Supervisor", "SUPERVISOR"},
                {"operator_readonly", "Tek4u2024", "operator_readonly", "operator_readonly"}
        };
    }
}
