package ui;

import controller.*;
import model.*;
import domain.*;
import value.Amount;
import view.*;

import java.util.*;

public class KioskUI {
    private final KioskController kioskController = new KioskController();
    private final PaymentController paymentController = new PaymentController();
    private final PrinterView printer = new PrinterView();
    private final PaymentView paymentView = new PaymentView();
    private final KeypadView keypad = new KeypadView();
    private final MenuView menuView = new MenuView();
    private final CartView cartView = new CartView();

    public void makePayment() {
        Amount total = kioskController.getCartController().getTotalAmount();
        PaymentResult result = paymentController.processPayment(total);
        paymentView.printPaymentResult(result.isSuccess(), result.getMessage());
    }

    public void checkTotalAmount() {
        paymentView.printTotalAmount(kioskController.getCartController().getTotalAmount());
    }

    public void printReceipt(PaymentInfo info) {
        printer.print(info);
    }

    public void returnSalesMenu() {
        List<Menu> sales = kioskController.getSalesMenu();
        menuView.showMenuList(sales);
    }

    public void returnMenuInfo(Menu menu) {
        menuView.showMenuDetail(menu);
    }

    public void returnMyCart(Menu menu, int quantity) {
        kioskController.addToCart(menu, quantity);
    }

    public void triggerKeypad() {
        keypad.open();
    }

    public void processCouponCode(String code) {
        keypad.inputCouponCode(code);
        keypad.submitCode(code);
        boolean result = paymentController.validateCoupon(code);
        printer.printCouponUsage(code, result);
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            menuView.printMainMenu();
            System.out.print("옵션을 골라주세요: ");
            int option = sc.nextInt();
            sc.nextLine();

            String category = null;
            if (option == 1) category = "Burgers";
            else if (option == 2) category = "Sides";
            else if (option == 3) category = "Drinks";

            if (category != null) {
                handleCategoryMenu(sc, category);
            } else if (option == 4) {
                handleCartMenu(sc);
            } else if (option == 5) {
                handlePayment(sc);
                break;
            }
        }
    }

    // KioskUI.java → handleCategoryMenu 리팩토링 부분
    private void handleCategoryMenu(Scanner sc, String category) {
        List<Menu> menus = kioskController.getMenuByCategory(category);
        menuView.showMenuList(menus);
        System.out.print("아이템 ID를 골라주세요 (나가기==0): ");
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0) return;

        Menu selected = menus.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
        if (selected != null) {
            boolean isSet = false;
            if (selected instanceof BurgerMenu) {
                System.out.print("세트로 하시겠습니까? (y/n): ");
                String yn = sc.nextLine();
                isSet = yn.equalsIgnoreCase("y");
            }
            System.out.print("수량을 입력해주세요: ");
            int qty = sc.nextInt();
            sc.nextLine();

            int price = selected.getPrice();
            if (isSet) price += 2000;

            kioskController.addMenuToCart(selected, qty, isSet, price);
            cartView.printAddToCart(selected.getName() + (isSet ? " (세트)" : ""), qty);
        }
    }
    // KioskUI.java → handleCartMenu 리팩토링 부분
    private void handleCartMenu(Scanner sc) {
        while (true) {
            List<CartItem> cartItems = kioskController.getCartItems();
            cartView.printCartItems(cartItems, kioskController.getCartTotalAmount());
            if (cartItems.isEmpty()) break;

            cartView.printCartMenu();
            System.out.print("선택: ");
            int cartOpt = sc.nextInt();
            sc.nextLine();

            if (cartOpt == 0) break;

            System.out.print("수정/삭제할 항목 번호 입력: ");
            int index = sc.nextInt() - 1;
            sc.nextLine();

            if (index < 0 || index >= cartItems.size()) {
                cartView.printNotFoundMenu();
                continue;
            }
            CartItem target = cartItems.get(index);

            if (cartOpt == 1) {
                System.out.print("새 수량 입력: ");
                int newQty = sc.nextInt();
                sc.nextLine();
                kioskController.updateCartItemQuantity(target.getMenu(), target.isSet(), newQty);
                cartView.printQuantityUpdate(target.getMenu().getName() + (target.isSet() ? " (세트)" : ""), newQty);
            } else if (cartOpt == 2) {
                kioskController.removeCartItem(target.getMenu(), target.isSet());
                cartView.printRemovedItem(target.getMenu().getName() + (target.isSet() ? " (세트)" : ""));
            }
        }
    }

    private void handlePayment(Scanner sc) {
        paymentView.printPaymentChoice();
        int payChoice = sc.nextInt();
        sc.nextLine();

        if (payChoice == 2) {
            System.out.print("쿠폰 코드 입력: ");
            String code = sc.nextLine();
            processCouponCode(code);

            if (paymentController.validateCoupon(code)) {
                paymentView.printPaymentResult(true, "");
                printReceipt(new PaymentInfo(new Amount(0), "기프티콘(쭌선진)"));
            } else {
                paymentView.printPaymentResult(false, "");
                paymentView.printInvalidCoupon();
            }
        } else if (payChoice == 1) {
            Amount total = kioskController.getCartController().getTotalAmount();
            PaymentResult result = paymentController.processPayment(total);

            paymentView.printPaymentResult(result.isSuccess(), result.getMessage());
            if (result.isSuccess()) {
                printReceipt(new PaymentInfo(total, "Card"));
            }
        } else {
            paymentView.printInvalidPaymentChoice();
        }
    }
}
