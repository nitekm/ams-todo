package ncode.dev.bffwife.model;

public enum PriorityEnum {
    P1("P1"),
    P2("P2"),
    P3("P3"),
    P4("P4"),
    P5("P5");

    private String value;

    public String getValue() {
        return value;
    }

    PriorityEnum(String value) {
        this.value = value;
    }
}
