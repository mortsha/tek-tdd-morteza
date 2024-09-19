package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.base.ApiTestsBase;

public class UserProfileTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(UserProfileTest.class);

    @Test(dataProvider = "positiveCredentials")
    public void getUserProfile(String tokenProvide, String expectedFullName, String expectedUsername) {
        String tokenGenerated = "";
        if(tokenProvide.equalsIgnoreCase("supervisor")){
            tokenGenerated = getTokenWithSupervisor();
        } else if (tokenProvide.equalsIgnoreCase("operator_readonly")) {
            tokenGenerated = getTokenWithUserReadOnly();

        }
        Response response = getDefaultRequest().header("Authorization", tokenGenerated)
                .when()
                .get(EndPoints.GET_PROFILE.getValue())
                .then().statusCode(200).extract().response();
        extentInfo(response.asPrettyString());
        LOGGER.info(response.prettyPrint());
        String actualFullName = response.body().jsonPath().getString("fullName");
        Assert.assertEquals(actualFullName, expectedFullName);
        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, expectedUsername);
    }

    @DataProvider
    private Object[][] positiveCredentials() {
        return new Object[][]{
                {"supervisor", "Supervisor", "SUPERVISOR"},
                {"operator_readonly", "operator_readonly", "operator_readonly"}
        };
    }
}
