package ui;

public class Keypad {
    private String currentInput = "";

    public void open() {
        System.out.println("[쿠폰 코드를 입력해 주세요]");
    }

    public void inputCouponCode(String code) {
        currentInput = code;
    }

    public void submitCode(String code) {
        // 쿠폰 코드 확인
        System.out.println("쿠폰 코드 제출됨: " + code);
    }
}
