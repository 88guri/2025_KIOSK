package model;

import value.Amount;

public abstract class Menu {
    protected int id;
    protected String name;
    protected String description;
    protected Amount price;
    protected String category;

    public Menu(int id, String name, String description, int price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = new Amount(price);
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price.getValue(); }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return id + ". " + name + " - " + price + "원";
    }
}