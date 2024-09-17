package tek.tdd.utility;

public enum EndPoints {
    GENERATE_TOKEN("/api/token");


    private final String value;

    EndPoints(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
