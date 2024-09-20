package tek.tdd.base;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import tek.tdd.api.models.request.CreateAccountRequest;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.api.models.request.TokenRequest;
import tek.tdd.api.models.response.TokenResponse;
import tek.tdd.utility.DataGenerator;

import java.util.HashMap;
import java.util.Map;

@Listeners({ExtentITestListenerClassAdapter.class})
public class ApiTestsBase extends BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(ApiTestsBase.class);

    public RequestSpecification getDefaultRequest() {
        LOGGER.info("Sending API call to {}", RestAssured.baseURI);
        return RestAssured.given().contentType(ContentType.JSON);
    }

    public Map<String, String> getTokenRequestBody(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username and password must not be null or empty");
        }
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        return requestBody;
    }

    public void extentInfo(String value) {
        ExtentTestManager.getTest().info(value);
    }

    public String getTokenWithSupervisor() {
        return ("Bearer ") + getDefaultRequest()
                .body(new TokenRequest("supervisor", "tek_supervisor"))
                .when()
                .post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response().body().jsonPath().getObject("", TokenResponse.class).getToken();
    }

    public String getTokenWithInputs(String username,String password){
        return ("Bearer ") + getDefaultRequest()
                .body(new TokenRequest(username, password))
                .when()
                .post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response().body().jsonPath().getObject("", TokenResponse.class).getToken();

    }

}
