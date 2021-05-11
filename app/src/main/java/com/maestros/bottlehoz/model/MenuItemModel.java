package com.maestros.bottlehoz.model;

public class MenuItemModel {

    int menuImage;
    String menuTitle,menuCount,recordscount;

    public MenuItemModel(int menuImage, String menuTitle, String menuCount,String recordscount) {
        this.menuImage = menuImage;
        this.menuTitle = menuTitle;
        this.menuCount = menuCount;
        this.recordscount = recordscount;
    }

    public String getRecordscount() {
        return recordscount;
    }

    public void setRecordscount(String recordscount) {
        this.recordscount = recordscount;
    }

    public MenuItemModel(String menuTitle) {

        this.menuTitle = menuTitle;

    }
    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(String menuCount) {
        this.menuCount = menuCount;
    }
}
