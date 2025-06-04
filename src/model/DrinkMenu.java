package model;

public class DrinkMenu extends Menu {
    private String size;

    public DrinkMenu(int id, String name, String description, int price, String size) {
        super(id, name, description, price, "Drinks");
        this.size = size;
    }

    public String getSize() { return size; }

    @Override
    public String toString() {
        return super.toString() + " (" + size + ")";
    }
}