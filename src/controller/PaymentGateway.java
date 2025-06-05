package controller;

import domain.AuthorizationResult;
import value.Amount;
import java.util.Random;

public class PaymentGateway {
    public AuthorizationResult authorize(Amount amount) {
        boolean approved = new Random().nextBoolean();
        String reason = approved ? "승인 완료" : "잔액 부족 또는 승인 거절";
        return new AuthorizationResult(approved, reason);
    }
}
