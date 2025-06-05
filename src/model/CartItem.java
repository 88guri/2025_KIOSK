package model;

import java.util.Objects;

public class CartItem {
    private Menu menu;
    private int quantity;
    private boolean isSet;
    private int unitPrice;
    private int totalPrice;

    public CartItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
        this.isSet = (menu instanceof BurgerMenu bm) && bm.isSet();
        this.unitPrice = menu.getPrice();
        this.totalPrice = unitPrice * quantity;
    }

    public CartItem(Menu menu, int quantity, boolean isSet, int price) {
        this.menu = menu;
        this.quantity = quantity;
        this.isSet = isSet;
        this.unitPrice = price;
        this.totalPrice = price * quantity;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = unitPrice * quantity;
    }

    public int getUnitPrice() { return unitPrice; }
    public int getTotalPrice() { return totalPrice; }
    public boolean isSet() { return isSet; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem other)) return false;
        if (this.getMenu().getId() != other.getMenu().getId()) return false;
        return this.isSet == other.isSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu.getId(), isSet);
    }
}