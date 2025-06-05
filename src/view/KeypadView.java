package view;

public class KeypadView {
    private String currentInput = "";

    public void open() {
        System.out.println("[기프티콘 코드를 입력해주세요]");
    }

    public void inputCouponCode(String code) {
        currentInput = code;
    }

    public void submitCode(String code) {
        System.out.println("기프티콘 코드 제출완료: " + code);
    }

    public String getCurrentInput() {
        return currentInput;
    }
}
