package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public int getTotalAmount() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
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

    public void updateItemQuantity(Menu menu, int qty) {
        for (CartItem item : items) {
            if (item.getMenu().getId() == menu.getId()) {
                item.setQuantity(qty);
                return;
            }
        }
    }

    public List<CartItem> getItems() {
        return items;
    }
}
