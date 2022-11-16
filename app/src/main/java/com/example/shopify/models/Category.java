package com.example.shopify.models;

public class Category {
    private String name, icon, color, brief;
    private int id;
public Category(String name, String icon, String color, String brief, int id){
    this.name = name;
    this.id = id;
    this.icon = icon;
    this.color = color;
    this.brief = brief;

}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBrief() {
        return brief;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
