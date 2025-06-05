package controller;

import model.Cart;
import model.Menu;
import model.CartItem;
import value.Amount;

import java.util.List;

public class CartController {
    private final Cart cart = new Cart();

    public void addToCart(Menu menu, int quantity) {
        cart.addToCart(menu, quantity);
    }

    public void updateItemQuantity(Menu menu, boolean isSet, int qty) {
        cart.updateItemQuantity(menu, isSet, qty);
    }

    public void removeFromCart(Menu menu, boolean isSet) {
        cart.removeFromCart(menu, isSet);
    }

    public List<CartItem> getCartItems() {
        return cart.getItems();
    }

    public Amount getTotalAmount() {
        return cart.getTotalAmount();
    }

    public boolean isEmpty() {
        return cart.getItems().isEmpty();
    }

    public void addItemDirect(CartItem item) {
        for (CartItem existing : cart.getItems()) {
            if (existing.equals(item)) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return;
            }
        }
        cart.getItems().add(item);
    }
}
