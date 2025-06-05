package view;

import model.*;

import java.util.List;

public class MenuView {

    public void showMenuList(List<Menu> menus) {
        System.out.println("메뉴 목록:");
        for (Menu menu : menus) {
            String type = "";

            if (menu instanceof DrinkMenu drink) {
                type = "[" + drink.getSize() + "]";
            } else if (menu instanceof SideMenu side) {
                type = "[" + side.getSize() + "]";
            }

            System.out.println("- " + menu.getId() + ". " + menu.getName() + " " + type + " - " + menu.getDescription() + " / " + menu.getPrice() + "원");
        }
    }

    public void showMenuDetail(Menu menu) {
        System.out.println("메뉴 정보:");
        printDetailedMenu(menu);
    }

    public void printDetailedMenu(Menu menu) {
        System.out.print("- " + menu.getName());
        if (menu instanceof DrinkMenu drink) {
            System.out.print(" [" + drink.getSize() + "]");
        } else if (menu instanceof SideMenu side) {
            System.out.print(" [" + side.getSize() + "]");
        }
        System.out.println(" - " + menu.getDescription() + " / " + menu.getPrice() + "원");
    }

    public void printMainMenu() {
        System.out.println("\n👩🏻‍🍳 쭌션진's BURGER KIOSK");
        System.out.println("1. 🍔 Burgers");
        System.out.println("2. 🍟 Sides");
        System.out.println("3. 🥤 Drinks");
        System.out.println("4. 🛒 My Cart");
        System.out.println("5. 💰 Payment");
    }
}
