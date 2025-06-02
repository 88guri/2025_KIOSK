package controller;

public class PaymentController {
    public boolean validateCoupon(String code) {
        return code.equals("DISCOUNT1000"); // 예시
    }

    public void processPayment(int amount) {
        System.out.println("💳 결제 완료! 총액: " + amount + "원");
    }
}
