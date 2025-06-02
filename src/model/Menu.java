package model;

public class Menu {
    private int id;
    private String name;
    private String description;
    private int price;
    private String category;

    public Menu(int id, String name, String description, int price, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getPrice() { return price; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return id + ". " + name + " - " + price + "원";
    }
}