package tek.tdd.api.test;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.enums.EndPoints;
import tek.tdd.api.models.response.PlanCodeResponse;
import tek.tdd.base.ApiTestsBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanCodeTest extends ApiTestsBase {

    private static final Logger LOGGER = LogManager.getLogger(PlanCodeTest.class);

    @Test
    public void validatePlanCode() {
        Response response = getDefaultRequest().header("Authorization", getTokenWithSupervisor())
                .when().get(EndPoints.GET_ALL_PLAN_CODE.getValue())
                .then().statusCode(200)
                .extract().response();
        LOGGER.info("Getting plan code " + response.prettyPrint());
        extentInfo(response.asPrettyString());

        List<PlanCodeResponse> plancodes = response.body().jsonPath().getList("", PlanCodeResponse.class);
        Assert.assertNotNull(plancodes);
        Assert.assertTrue(plancodes.size() == 4,"Plan code list should match");
        List<String> expectedPlanType = new ArrayList<>();
        expectedPlanType.add("MOTORCYCLE");
        expectedPlanType.add("BOAT");
        expectedPlanType.add("RENTERS");
        expectedPlanType.add("AUTO");

        List<String> actualPlanType = new ArrayList<>();
        for(PlanCodeResponse planCode:plancodes){
            Assert.assertFalse(planCode.isPlanExpired());
            String planType = planCode.getPlanType();
            actualPlanType.add(planType);


        }
        Collections.sort(expectedPlanType);
        Collections.sort(actualPlanType);
        Assert.assertEquals(actualPlanType,expectedPlanType,"Both plan type should match");
    }
}
