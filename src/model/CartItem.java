package model;

public class CartItem {
    private Menu menu;
    private int quantity;
    private int unitPrice;
    private int totalPrice;

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.unitPrice = menu.getPrice();
        this.totalPrice = unitPrice * quantity;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }
    public int getUnitPrice() { return unitPrice; }
    public int getTotalPrice() { return totalPrice; }
}
