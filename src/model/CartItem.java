package model;

public class CartItem {
    private Menu menu;
    private int quantity;

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getUnitPrice() { return menu.getPrice(); }
    public int getTotalPrice() { return getUnitPrice() * quantity; }
}
