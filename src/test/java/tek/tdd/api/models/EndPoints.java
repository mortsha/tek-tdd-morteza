package tek.tdd.api.models;

import lombok.Getter;

@Getter
public enum EndPoints {
    POST_GENERATE_TOKEN("/api/token"),
    GET_ACCOUNT("/api/accounts/get-account"),
    GET_PRIMARY_ACCOUNT("/api/accounts/get-primary-account"),
    GET_ALL_ACCOUNT("/api/accounts/get-all-accounts"),
    GET_ALL_PLAN_CODE("/api/plans/get-all-plan-code"),
    GET_PROFILE("/api/user/profile"),
    POST_ADD_PRIMARY_ACCOUNT("/api/accounts/add-primary-account"),
    POST_ADD_ACCOUNT_PHONE("/api/accounts/add-account-phone"),
    POST_ADD_ACCOUNT_CAR("/api/accounts/add-account-car"),
    POST_ADD_ACCOUNT_ADDRESS("/api/accounts/add-account-address"),
    DELETE_ACCOUNT("/api/accounts/delete-account"),
    DELETE_ACCOUNT_PHONE("/api/accounts/delete-phone"),
    DELETE_ACCOUNT_CAR("/api/accounts/delete-car"),
    DELETE_ACCOUNT_ADDRESS("/api/accounts/delete-address"),

    ;


    private final String value;

    EndPoints(String value) {
        this.value = value;
    }

}
