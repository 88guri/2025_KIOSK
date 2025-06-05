package view;

import model.CartItem;
import value.Amount;

import java.util.List;

public class CartView {

    public void printAddToCart(String itemName, int qty) {
        System.out.println(qty + " x " + itemName + " 장바구니에 추가됨!");
    }

    public void printCartItems(List<CartItem> items, Amount total) {
        System.out.println("🛒 장바구니 목록:");
        if (items.isEmpty()) {
            System.out.println("(비어 있음)");
        } else {
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                String name = item.getMenu().getName();
                if (item.isSet()) {
                    name += " (세트)";
                }
                System.out.println((i + 1) + ". " +
                        name + " x" + item.getQuantity() +
                        " (단가: " + item.getUnitPrice() + "원) = " + item.getTotalPrice() + "원");
            }
            System.out.println("총합: " + total);
        }
    }

    public void printCartMenu() {
        System.out.println("1. 수량 수정");
        System.out.println("2. 항목 삭제");
        System.out.println("0. 뒤로가기");
    }

    public void printNotFoundMenu() {
        System.out.println("해당 ID의 메뉴를 찾을 수 없습니다.");
    }

    public void printQuantityUpdate(String name, int qty) {
        System.out.println(name + " 수량이 " + qty + "개로 변경되었습니다!");
    }

    public void printRemovedItem(String name) {
        System.out.println(name + "이(가) 장바구니에서 삭제되었습니다!");
    }
}
