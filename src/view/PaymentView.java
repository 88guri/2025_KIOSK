package view;

import value.Amount;

public class PaymentView {

    public void printPaymentResult(boolean success, String reason) {
        if (success) {
            System.out.println("결제가 완료되었습니다.");
        } else {
            System.out.println("결제에 실패했습니다.");
            if (!reason.isEmpty()) {
                System.out.println("사유: " + reason);
            }
        }
    }

    public void printSelectedPayment(String type) {
        System.out.println("선택된 결제 수단: " + type);
    }

    public void printPaymentChoice() {
        System.out.println("결제 방식을 선택하세요:");
        System.out.println("1. 카드 결제");
        System.out.println("2. 기프티콘 결제");
    }

    public void printInvalidCoupon() {
        System.out.println("유효하지 않은 기프티콘입니다.");
    }

    public void printFailureReason(String reason) {
        System.out.println("사유: " + reason);
    }

    public void printInvalidPaymentChoice() {
        System.out.println("유효하지 않은 결제 선택입니다.");
    }

    public void printTotalAmount(Amount amount) {
        System.out.println("총 결제 금액: " + amount);
    }
}
