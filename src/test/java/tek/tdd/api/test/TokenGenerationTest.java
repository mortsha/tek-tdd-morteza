package tek.tdd.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import tek.tdd.base.ApiTestsBase;

import java.util.HashMap;
import java.util.Map;

public class TokenGenerationTest extends ApiTestsBase {

    @Test
    public void generateTokenWithSupervisor(){
        RequestSpecification request =  getDefaultRequest();
        request.body(RequestBody.supervisorUserRequest());
        Response response = request.when().post("/api/token");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
