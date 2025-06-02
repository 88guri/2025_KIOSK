package controller;

import model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuController {
    private List<Menu> menuList = new ArrayList<>();

    public MenuController() {
        menuList.add(new Menu(1, "불고기버거", "고기 가득", 3000, "Burgers"));
        menuList.add(new Menu(2, "불고기버거 세트", "버거+감튀+콜라", 5000, "Burgers"));
        menuList.add(new Menu(3, "감자튀김(R)", "사이드 감튀", 2000, "Sides"));
        menuList.add(new Menu(4, "콜라(R)", "시원한 콜라", 1500, "Drinks"));
        // 추가하자
    }

    public List<Menu> getSalesMenu() {
        return menuList;
    }

    public List<Menu> getMenuInfo() {
        return menuList;
    }

    public List<Menu> getMenuByCategory(String category) {
        List<Menu> filtered = new ArrayList<>();
        for (Menu m : menuList) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                filtered.add(m);
            }
        }
        return filtered;
    }
}
