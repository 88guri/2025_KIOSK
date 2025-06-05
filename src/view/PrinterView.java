package view;

import domain.PaymentInfo;

public class PrinterView {

    public void print(PaymentInfo info) {
        System.out.println("🧾 ---- 영수증 ----");
        System.out.println(info.toString());
        System.out.println("------------------");
    }

    public void printCouponUsage(String code, boolean success) {
        System.out.println("기프티콘 " + code + (success ? " 사용 성공!" : " 사용 실패!"));
    }
}
