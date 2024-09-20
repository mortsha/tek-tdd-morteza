package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.enums.AccountType;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.api.models.request.TokenRequest;
import tek.tdd.api.models.response.UserProfileResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(UserProfileTest.class);

    @Test(dataProvider = "positiveCredentials")
    public void getUserProfile(String username, String password, String expectedUsername, String expectedRole) {
        String token = getTokenWithInputs(username, password);

        Response response = getDefaultRequest().header("Authorization", token)
                .when()
                .get(EndPoints.GET_PROFILE.getValue())
                .then().statusCode(200)
                .extract().response();
        extentInfo(response.asPrettyString());
        LOGGER.info(response.prettyPrint());
        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, expectedUsername, "username should match");

        String actualAuthRole = response.body().jsonPath().getString("authorities[0].role");
        Assert.assertEquals(actualAuthRole, expectedRole, "Authority role should match");
    }

    @Test(dataProvider = "users")
    public void validateUserInfo(TokenRequest request) {
        DatabaseUtility dbUtility = new DatabaseUtility();
        String query = "select id,full_name, username, account_type from user where username = '{username}';".replace("{username}",request.getUsername());
        List<Map<String, Object>> expectedDataList = dbUtility.getResultFromQuery(query);
        Assert.assertFalse(expectedDataList.isEmpty());
        Map<String, Object> expectedMap = expectedDataList.get(0);

        Response response = authenticateRequest(request)
                .when()
                .get(EndPoints.GET_PROFILE.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();
        extentInfo(response.asPrettyString());

        UserProfileResponse userProfile = response.body().jsonPath().getObject("", UserProfileResponse.class);
        Assert.assertEquals(userProfile.getUsername(), expectedMap.get("username").toString());
        Assert.assertEquals(userProfile.getFullName(), expectedMap.get("full_name").toString());
        Assert.assertEquals(userProfile.getAccountType().name(), expectedMap.get("account_type").toString());
        Assert.assertEquals(userProfile.getId(), Integer.parseInt(expectedMap.get("id").toString()));


    }

    @DataProvider
    private TokenRequest[] users() {
        return new TokenRequest[]{
                new TokenRequest("supervisor", "tek_supervisor"),
                new TokenRequest("operator_readonly", "Tek4u2024"),
                new TokenRequest("mori12345", "mori1234")

        };
    }

    @DataProvider
    private Object[][] positiveCredentials() {
        return new Object[][]{
                {"supervisor", "tek_supervisor", "SUPERVISOR", "ADMIN"},
                {"operator_readonly", "Tek4u2024", "operator_readonly", "READ_ONLY"},


        };
    }
}
