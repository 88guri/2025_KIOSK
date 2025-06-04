package model;

import value.Amount;
import java.util.*;

public class Cart {
    private int totalAmount;
    private List<CartItem> items = new ArrayList<>();

    public Amount getTotalAmount() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return new Amount(total);
    }

    public void addToCart(Menu menu, int quantity) {
        for (CartItem item : items) {
            if (item.getMenu().getId() == menu.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(menu, quantity));
    }

    public void removeFromCart(Menu menu) {
        items.removeIf(item -> item.getMenu().getId() == menu.getId());
    }

    public void updateItemQuantity(Menu menu, int newQty) {
        for (CartItem item : items) {
            if (item.getMenu().getId() == menu.getId()) {
                if (newQty <= 0) {
                    removeFromCart(menu);
                } else {
                    item.setQuantity(newQty);
                }
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }
}
