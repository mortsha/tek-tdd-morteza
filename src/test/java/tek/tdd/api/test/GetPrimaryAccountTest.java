package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.base.ApiTestsBase;

public class GetPrimaryAccountTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(GetPrimaryAccountTest.class);

    @Test
    public void getPrimaryAccountValidate() {
        RequestSpecification requestSpecification = getDefaultRequest();
        requestSpecification.queryParam("primaryPersonId", 228);
        Response response = requestSpecification.when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue());
        response.then().statusCode(200);
        response.prettyPrint();
        String email = response.jsonPath().getString("email");
        Assert.assertEquals(email, "mori1234@gmail.com", "Email should match");
        LOGGER.info("Getting account primary " + response.asPrettyString());
        ExtentTestManager.getTest().info("Getting account primary " + response.asPrettyString());

    }

    @Test
    public void validateGetAccountNotExist() {
        int id = 8028218;
        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", id)
                .when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then().statusCode(404)
                .extract().response();

        String actualError = response.jsonPath().getString("errorMessage");
        Assert.assertEquals(actualError, "Account with id " + id + " not exist");
        LOGGER.info("Getting account primary with invalid data " + response.asPrettyString());
        extentInfo("Getting account primary with invalid data " + response.asPrettyString());
    }

    @Test
    public void getAccountWithAuth(){
        int id = 228;
        String token = getTokenWithSupervisor();
        Response response = getDefaultRequest().header("Authorization",token).queryParam("primaryPersonId",id)
                .when().get(EndPoints.GET_ACCOUNT.getValue())
                .then().statusCode(200).extract().response();

        response.prettyPrint();
    }
}
