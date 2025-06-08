package controller;

import model.CartItem;
import model.Menu;
import value.Amount;

import java.util.List;

public class KioskController {
    private final MenuController menuController = new MenuController();
    private final CartController cartController = new CartController();

    public List<Menu> getMenuByCategory(String category) {
        return menuController.getMenuByCategory(category);
    }

    public List<Menu> getSalesMenu() {
        return menuController.getSalesMenu();
    }

    public void addToCart(Menu menu, int quantity) {
        cartController.addToCart(menu, quantity);
    }

    public CartController getCartController() {
        return cartController;
    }

    public void addMenuToCart(Menu menu, int qty, boolean isSet, int price) {
        CartItem item = new CartItem(menu, qty, isSet, price);
        cartController.addItemDirect(item);
    }

    public List<CartItem> getCartItems() {
        return cartController.getCartItems();
    }

    public void updateCartItemQuantity(Menu menu, boolean isSet, int qty) {
        cartController.updateItemQuantity(menu, isSet, qty);
    }

    public void removeCartItem(Menu menu, boolean isSet) {
        cartController.removeFromCart(menu, isSet);
    }

    public Amount getCartTotalAmount() {
        return cartController.getTotalAmount();
    }
}
