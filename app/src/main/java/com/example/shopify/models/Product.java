package com.example.shopify.models;

public class Product {
    private String name, image, status;
    private double price, discount;
    private int stock, id;
public Product(String name,String  image,String  status,double price, double discount,  int stock, int id ){
    this.name = name;
    this.image = image;
    this.status = status;
    this.price = price;
    this.discount = discount;
    this.stock = stock;
    this.id = id;
}
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImage() {
        return image;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
