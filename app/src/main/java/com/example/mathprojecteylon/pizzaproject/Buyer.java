package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

public class Buyer extends User{
    private String adress;
    private int phoneNum;
    private ArrayList<Pizza> cart;
    public static Buyer currentBuyer;
    public Buyer(String firstName, String lastName,String email,int pass,int passConiform,String adress,int phoneNum
    ) {
        super(firstName, lastName, email, pass, passConiform);
        this.adress = adress;
        this.phoneNum = phoneNum;
        this.cart=new ArrayList<Pizza>();

    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public ArrayList<Pizza> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Pizza> cart) {
        this.cart = cart;
    }
    public void addToCart(Pizza pizza){
        cart.add(pizza);
    }
}
