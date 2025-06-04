package ui;

import domain.PaymentInfo;

public class Printer {
    public void print(PaymentInfo info) {
        System.out.println("---- 영수증 ----");
        System.out.println(info.toString());
        System.out.println("---------------");
    }

    public void printCouponUsage(String code, boolean success) {
        System.out.println("쿠폰 " + code + (success ? " 사용 성공!" : " 사용 실패!"));
    }
}
