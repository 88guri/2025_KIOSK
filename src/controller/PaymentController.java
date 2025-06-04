package controller;

import domain.*;
import value.Amount;
import java.util.*;

public class PaymentController {
    private String selectedMethod;

    public PaymentResult processPayment(Amount amount) {
        AuthorizationResult auth = requestAuthorization(amount);
        return new PaymentResult(auth.isApproved(), auth.getReason());
    }

    public AuthorizationResult requestAuthorization(Amount amount) {
        return new PaymentGateway().authorize(amount);
    }

    public void setPaymentMethod() {
        this.selectedMethod = "Card"; // 기본값 예시
    }

    public List<PaymentOption> getAvailableOptions() {
        return List.of(new PaymentOption("Card", "신용카드"), new PaymentOption("Cash", "현금"));
    }

    public void validateAndSetPaymentMethod(String optionType) {
        storeSelectedMethod(optionType);
    }

    public void storeSelectedMethod(String optionType) {
        this.selectedMethod = optionType;
    }

    public boolean validateCoupon(String code) {
        return code.equals("쭌선진");
    }
}
