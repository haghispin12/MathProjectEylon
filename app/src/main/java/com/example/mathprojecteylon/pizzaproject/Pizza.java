package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

public class Pizza {
    private int price;
    private String size;
    private ArrayList<String> extras;


    public Pizza(int price, String size, ArrayList<String> extras) {
        this.price = price;
        this.size = size;
        this.extras = extras;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ArrayList<String> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<String> extras) {
        this.extras = extras;
    }
}
