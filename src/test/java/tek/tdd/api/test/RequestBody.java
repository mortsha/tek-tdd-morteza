package tek.tdd.api.test;

import java.util.HashMap;
import java.util.Map;

public class RequestBody {

    public static Map<String, String> supervisorUserRequest() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "supervisor");
        requestBody.put("password", "tek_supervisor");
        return requestBody;
    }

    public static Map<String, String> readOnlyUser() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "operator_readonly");
        requestBody.put("password", "Tek4u2024");
        return requestBody;
    }

}
