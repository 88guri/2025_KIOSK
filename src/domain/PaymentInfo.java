package domain;

import value.Amount;

public class PaymentInfo {
    private final Amount amount;
    private final String method;

    public PaymentInfo(Amount amount, String method) {
        this.amount = amount;
        this.method = method;
    }

    public Amount getAmount() { return amount; }
    public String getMethod() { return method; }

    @Override
    public String toString() {
        return "결제 방법: " + method + " / 금액: " + amount;
    }
}
