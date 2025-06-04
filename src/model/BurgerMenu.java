package model;

public class BurgerMenu extends Menu {
    private boolean isSet;

    public BurgerMenu(int id, String name, String description, int price, boolean isSet) {
        super(id, name, description, price, "Burgers");
        this.isSet = isSet;
    }

    public boolean isSet() { return isSet; }

    @Override
    public String toString() {
        return super.toString() + (isSet ? " (세트)" : " (단품)");
    }
}