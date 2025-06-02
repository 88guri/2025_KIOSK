package ui;

public class Printer {
    public void print(String info) {
        System.out.println("=== 영수증 출력 ===");
        System.out.println(info);
    }

    public void printCouponUsage(String code, boolean success) {
        if (success) {
            System.out.println("쿠폰 " + code + " 사용 성공!");
        } else {
            System.out.println("쿠폰 " + code + " 사용 실패!");
        }
    }
}
