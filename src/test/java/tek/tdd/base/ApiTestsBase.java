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
        TokenRequest request = new TokenRequest("supervisor", "tek_supervisor");

        Response response = getDefaultRequest().body(request).when().post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response();
        LOGGER.info("Getting token with supervisor user " + response.prettyPrint());
        extentInfo(response.asPrettyString());

        TokenResponse token = response.body().jsonPath().getObject("", TokenResponse.class);
        return ("Bearer ") + token.getToken();
    }

    public String getTokenWithUserReadOnly(){
        TokenRequest request = new TokenRequest("operator_readonly", "Tek4u2024");

        Response response = getDefaultRequest().body(request).when().post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response();
        LOGGER.info("Getting token with readOnly user " + response.prettyPrint());
        extentInfo(response.asPrettyString());

        TokenResponse token = response.body().jsonPath().getObject("", TokenResponse.class);
        return ("Bearer ") + token.getToken();
    }

    public String getTokenWithInputs(String username,String password){
        TokenRequest request = new TokenRequest(username, password);

        Response response = getDefaultRequest().body(request).when().post(EndPoints.POST_GENERATE_TOKEN.getValue())
                .then().statusCode(200)
                .extract().response();
        LOGGER.info("Getting token with readOnly user " + response.prettyPrint());
        extentInfo(response.asPrettyString());

        TokenResponse token = response.body().jsonPath().getObject("", TokenResponse.class);
        return ("Bearer ") + token.getToken();
    }

}
