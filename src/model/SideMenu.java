package model;

public class SideMenu extends Menu {
    private String size;

    public SideMenu(int id, String name, String description, int price, String size) {
        super(id, name, description, price, "Sides");
        this.size = size;
    }

    public String getSize() { return size; }

    @Override
    public String toString() {
        return super.toString() + " (" + size + ")";
    }
}