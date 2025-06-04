package ui;

import controller.*;
import model.*;
import domain.*;
import value.*;

import java.util.*;

public class KioskUI {
    private Cart cart = new Cart();
    private MenuController menuController = new MenuController();
    private PaymentController paymentController = new PaymentController();
    private Printer printer = new Printer();
    private Keypad keypad = new Keypad();

    public void makePayment() {
        Amount total = cart.getTotalAmount();
        PaymentResult result = paymentController.processPayment(total);
        if (result.isSuccess()) showPaymentSuccess();
        else showPaymentFailed();
    }

    public void checkTotalAmount() {
        System.out.println("총액: " + cart.getTotalAmount());
    }

    public void showPaymentSuccess() {
        System.out.println("결제가 성공적으로 완료되었습니다!");
    }

    public void showPaymentFailed() {
        System.out.println("결제에 실패했습니다!");
    }

    public void printReceipt(PaymentInfo info) {
        printer.print(info);
    }

    public void returnSalesMenu() {
        List<Menu> sales = menuController.getSalesMenu();
        for (Menu menu : sales) {
            System.out.println(menu);
        }
    }

    public void returnMenuInfo(Menu menu) {
        System.out.println(menu.toString());
    }

    public void returnMyCart(Menu menu, int quantity) {
        cart.addToCart(menu, quantity);
    }

    public void selectPaymentOption() {
        List<PaymentOption> options = paymentController.getAvailableOptions();
        for (PaymentOption option : options) {
            System.out.println(option);
        }
    }

    public void showSelectedOption(String optionType) {
        System.out.println("선택된 결제 수단: " + optionType);
    }

    public void triggerKeypad() {
        keypad.open();
    }

    public void processCouponCode(String code) {
        keypad.inputCouponCode(code);
        keypad.submitCode(code);
        boolean result = paymentController.validateCoupon(code);
        notifyCouponResult(result);
    }

    public void notifyCouponResult(boolean result) {
        printer.printCouponUsage("입력코드", result);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n👩🏻‍🍳 쭌션진's BURGER KIOSK 😰");
            System.out.println("1. 🍔 Burgers");
            System.out.println("2. 🍟 Sides");
            System.out.println("3. 🥤 Drinks");
            System.out.println("4. 🛒 My Cart");
            System.out.println("5. 💰 Payment");
            System.out.print("Please select an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            String category = null;
            if (option == 1) category = "Burgers";
            else if (option == 2) category = "Sides";
            else if (option == 3) category = "Drinks";

            if (category != null) {
                List<Menu> menus = menuController.getMenuByCategory(category);
                for (Menu m : menus) {
                    System.out.println(m);
                }
                System.out.print("Select item ID (0 to return): ");
                int id = sc.nextInt();
                sc.nextLine();
                if (id == 0) continue;
                Menu selected = menus.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
                if (selected != null) {
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    returnMyCart(selected, qty);
                    System.out.println(qty + " x " + selected.getName() + " added to cart!");
                }
            } else if (option == 4) {
                while (true) {
                    System.out.println("🛒 장바구니 목록:");
                    List<CartItem> cartItems = cart.getItems();
                    if (cartItems.isEmpty()) {
                        System.out.println("(비어 있음)");
                        break;
                    }

                    for (CartItem item : cartItems) {
                        System.out.println("- ID: " + item.getMenu().getId() + " | " +
                                item.getMenu().getName() + " x" + item.getQuantity() +
                                " = " + item.getTotalPrice() + "원");
                    }

                    System.out.println("총합: " + cart.getTotalAmount());
                    System.out.println("1. 수량 수정");
                    System.out.println("2. 항목 삭제");
                    System.out.println("0. 뒤로가기");
                    System.out.print("선택: ");
                    int cartOpt = sc.nextInt();
                    sc.nextLine();

                    if (cartOpt == 0) break;

                    System.out.print("수정/삭제할 메뉴 ID 입력: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Menu selected = menuController.getSalesMenu().stream()
                            .filter(m -> m.getId() == id)
                            .findFirst().orElse(null);

                    if (selected == null) {
                        System.out.println("해당 ID의 메뉴를 찾을 수 없습니다.");
                        continue;
                    }

                    if (cartOpt == 1) {
                        System.out.print("새 수량 입력: ");
                        int newQty = sc.nextInt();
                        sc.nextLine();
                        cart.updateItemQuantity(selected, newQty);
                        System.out.println(selected.getName() + " 수량이 " + newQty + "개로 변경되었어요!");
                    } else if (cartOpt == 2) {
                        cart.removeFromCart(selected);
                        System.out.println(selected.getName() + "이(가) 장바구니에서 삭제되었어요!");
                    }
                }
            } else if (option == 5) {
                System.out.println("결제 방식을 선택하세요:");
                System.out.println("1. 💳 카드 결제");
                System.out.println("2. 🔖 기프티콘 결제");
                System.out.print("선택: ");
                int payChoice = sc.nextInt();
                sc.nextLine();

                if (payChoice == 2) {
                    System.out.print("쿠폰 코드 입력: ");
                    String code = sc.nextLine();
                    processCouponCode(code);

                    if (paymentController.validateCoupon(code)) {
                        showPaymentSuccess();
                        printReceipt(new PaymentInfo(new Amount(0), "기프티콘(쭌선진)"));
                    } else {
                        showPaymentFailed();
                        System.out.println("유효하지 않은 기프티콘입니다.");
                    }
                } else if (payChoice == 1) {
                    Amount total = cart.getTotalAmount();
                    PaymentResult result = paymentController.processPayment(total);

                    if (result.isSuccess()) {
                        showPaymentSuccess();
                        printReceipt(new PaymentInfo(total, "Card"));
                    } else {
                        showPaymentFailed();
                        System.out.println("사유: " + result.getMessage());
                    }
                } else {
                    System.out.println("유효하지 않은 결제 선택입니다.");
                }
                break;
            }
        }
    }
}
