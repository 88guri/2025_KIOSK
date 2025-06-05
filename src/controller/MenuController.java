package controller;

import model.*;
import java.util.*;

public class MenuController {
    private List<Menu> menuList = new ArrayList<>();

    public MenuController() {
        menuList.add(new BurgerMenu(1, "불고기버거", "고기 가득", 3000, false));
        menuList.add(new BurgerMenu(2, "치즈버거", "치즈 가득", 2500, false));
        menuList.add(new BurgerMenu(3, "새우버거", "통통한 새우패티", 4000, false));

        menuList.add(new SideMenu(4, "감자튀김", "사이드 감튀", 2000, "R"));
        menuList.add(new SideMenu(5, "감자튀김", "사이드 감튀", 2500, "L"));
        menuList.add(new SideMenu(6, "양파튀김", "사이드 양튀", 3000, "R"));
        menuList.add(new SideMenu(7, "양파튀김", "사이드 양튀", 4000, "L"));
        menuList.add(new SideMenu(8, "아이스크림", "사이드 아이스크림", 1500, "R"));

        menuList.add(new DrinkMenu(9, "콜라", "시원한 콜라", 1500, "R"));
        menuList.add(new DrinkMenu(10, "콜라", "시원한 콜라", 2000, "L"));
        menuList.add(new DrinkMenu(11, "사이다", "시원한 사이다", 1500, "R"));
        menuList.add(new DrinkMenu(12, "사이다", "시원한 사이다", 2000, "L"));
        menuList.add(new DrinkMenu(13, "오렌지주스", "상큼한 오렌지주스", 3000, "R"));
        menuList.add(new DrinkMenu(14, "오렌지주스", "상큼한 오렌지주스", 4000, "L"));
    }

    public List<Menu> getSalesMenu() {
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
