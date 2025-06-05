package controller;

import model.Menu;
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
}
