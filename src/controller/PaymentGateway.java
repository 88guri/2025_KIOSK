package controller;

import java.util.Random;
import domain.AuthorizationResult;
import value.Amount;

public class PaymentGateway {
    public AuthorizationResult authorize(Amount amount) {
        // 무작위 승인
        boolean approved = new Random().nextBoolean();
        String reason = approved ? "승인 완료" : "잔액 부족";
        return new AuthorizationResult(approved, reason);
    }
}
