package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.CreateAccountRequest;
import tek.tdd.api.models.CreateAccountResponse;
import tek.tdd.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;

public class CreateAccountTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(CreateAccountTest.class);

    @Test
    public void createAccountAndValidate() {
        CreateAccountRequest createAccountBody = createAccountData();
        Response response = getDefaultRequest()
                .body(createAccountBody).when().post(EndPoints.POST_ADD_PRIMARY_ACCOUNT.getValue())
                .then().statusCode(201).extract().response();

        LOGGER.info(response.prettyPrint());
        extentInfo(response.asPrettyString());
        CreateAccountResponse createAccountResponse = response.body().jsonPath().getObject("", CreateAccountResponse.class);

        String actualEmail = createAccountResponse.getEmail();
        Assert.assertEquals(actualEmail, createAccountBody.getEmail(), "Email should match");

    }
}
