package com.example.mathprojecteylon.pizzaproject;
import java.util.ArrayList;

public class order {
    private String email;
    private ArrayList<Pizza> cart;
    private int totalPrice;
    private String status;

    public order() {}

    public order(String email, ArrayList<Pizza> cart, int totalPrice) {
        this.email = email;
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.status = "ממתין";
    }
    // גט וסט
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public ArrayList<Pizza> getCart() { return cart; }
    public void setCart(ArrayList<Pizza> cart) { this.cart = cart; }
    public void addToCart(Pizza pizza) {
        cart.add(pizza);
    }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}