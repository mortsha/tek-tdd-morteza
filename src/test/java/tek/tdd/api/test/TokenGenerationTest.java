package tek.tdd.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import tek.tdd.base.ApiTestsBase;

import java.util.HashMap;
import java.util.Map;

public class TokenGenerationTest extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(TokenGenerationTest.class);

    @Test
    public void generateTokenWithSupervisor(){
        RequestSpecification request =  getDefaultRequest();
        request.body(RequestBody.supervisorUserRequest());
        Response response = request.when().post(Methods.apiToken);
        response.then().statusCode(200);
        LOGGER.info("Response is {} ",response.prettyPrint());

    }

    @Test
    public void generateTokenWithReadOnly(){
        RequestSpecification request = getDefaultRequest();
        request.body(RequestBody.readOnlyUser());
        Response response = request.when().post(Methods.apiToken);
        response.then().statusCode(200);
        LOGGER.info("Response is {} ",response.prettyPrint());
    }
}
