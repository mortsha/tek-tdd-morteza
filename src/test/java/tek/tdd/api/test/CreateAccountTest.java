package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.request.CreateAccountRequest;
import tek.tdd.api.models.response.CreateAccountResponse;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.base.ApiTestsBase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

        Assert.assertNotNull(createAccountResponse);

        String actualEmail = createAccountResponse.getEmail();
        Assert.assertEquals(actualEmail, createAccountBody.getEmail(), "Email should match");
        Assert.assertEquals(formatDob(createAccountResponse.getDateOfBirth()), createAccountBody.getDateOfBirth(), "Date of birth should match");
    }
    public String formatDob(int[] arrays){
        String arrayToString =  Arrays.toString(arrays).replaceAll(", ","-").replaceAll("\\[","").replaceAll("\\]","");
        DateTimeFormatter inputFormat1 = DateTimeFormatter.ofPattern("yyyy-M-d");
        DateTimeFormatter outputFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(arrayToString,inputFormat1);
        return date.format(outputFormat1);
    }
}
