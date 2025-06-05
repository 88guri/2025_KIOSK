package model;

import value.Amount;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public Amount getTotalAmount() {
        int total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return new Amount(total);
    }

    public void addToCart(Menu menu, int quantity) {
        for (CartItem item : items) {
            if (item.getMenu().getId() == menu.getId() && item.isSet() == ((menu instanceof BurgerMenu bm) && bm.isSet())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(menu, quantity));
    }

    public void removeFromCart(Menu menu, boolean isSet) {
        items.removeIf(item -> item.getMenu().getId() == menu.getId() && item.isSet() == isSet);
    }

    public void updateItemQuantity(Menu menu, boolean isSet, int newQty) {
        for (CartItem item : items) {
            if (item.getMenu().getId() == menu.getId() && item.isSet() == isSet) {
                if (newQty <= 0) {
                    removeFromCart(menu, isSet);
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