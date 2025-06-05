package domain;

public class PaymentOption {
    private final String type;
    private final String description;

    public PaymentOption(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() { return type; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return type + " - " + description;
    }
}
