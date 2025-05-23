package com.example.miu_restaurant;

public class Food {

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private int image;
    private String name;
    private String price;

    public Food(int image, String name, String price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }
}
