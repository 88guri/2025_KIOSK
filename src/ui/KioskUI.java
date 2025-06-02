package ui;

import controller.MenuController;
import controller.PaymentController;
import model.Cart;
import model.CartItem;
import model.Menu;

import java.util.*;

public class KioskUI {
    private Scanner sc = new Scanner(System.in);
    private MenuController menuController = new MenuController();
    private Cart cart = new Cart();
    private PaymentController paymentController = new PaymentController();
    private Printer printer = new Printer();

    public void run() {
        while (true) {
            System.out.println("\n쭌션진 키오스크 입니다 !");
            System.out.println("1. Burgers");
            System.out.println("2. Sides");
            System.out.println("3. Drinks");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.print("Please select an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: case 2: case 3:
                    String category = (option == 1) ? "Burgers" : (option == 2) ? "Sides" : "Drinks";
                    List<Menu> menus = menuController.getMenuByCategory(category);
                    for (Menu m : menus) {
                        System.out.println(m.toString());
                    }
                    System.out.print("Select item ID (0 to return): ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (id == 0) break;
                    Menu selected = menus.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
                    if (selected != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        cart.addToCart(selected, qty);
                        System.out.println(qty + " x " + selected.getName() + " added to cart!");
                    }
                    break;
                case 4:
                    System.out.println("🛒 장바구니 목록:");
                    for (CartItem item : cart.getItems()) {
                        System.out.println("- " + item.getMenu().getName() + " x" + item.getQuantity() + " = " + item.getTotalPrice() + "원");
                    }
                    System.out.println("총합: " + cart.getTotalAmount() + "원");
                    break;
                case 5:
                    System.out.print("쿠폰 코드 입력 (없으면 Enter): ");
                    String code = sc.nextLine();
                    if (!code.isBlank()) {
                        boolean success = paymentController.validateCoupon(code);
                        printer.printCouponUsage(code, success);
                        if (success) {
                            System.out.println("000원 할인 적용");
                        }
                    }
                    paymentController.processPayment(cart.getTotalAmount());
                    printer.print("총 결제 금액: " + cart.getTotalAmount() + "원");
                    return;
                default:
                    System.out.println("잘못된 선택입니다!");
            }
        }
    }
}
