package com.example.mathprojecteylon.pizzaproject;

import android.widget.EditText;

import java.util.ArrayList;

public class User {
    private String FirstNameS;
    private String LastNameS;
    private String adressS;
    private String userNS;
    private String emailS;
    private int phoneS;
    private int passS;
    private int passConfiormS;

    public User(String FirstNameS,String LastNameS,String adressS,String userNS,String emailS,
                int phoneS,int passS,int passConfiormS){
        this.FirstNameS=FirstNameS;
        this.LastNameS=LastNameS;
        this.adressS=adressS;
        this.userNS=userNS;
        this.emailS=emailS;
        this.phoneS=phoneS;
        this.passS=passS;
        this.passConfiormS=passConfiormS;

    }
    public static ArrayList<User> users = new ArrayList<>();//מה שנותן את היכולת לשמור
    public String getFirstNameS() {
        return FirstNameS;
    }

    public void setFirstNameS(String firstNameS) {
        FirstNameS = firstNameS;
    }

    public String getLastNameS() {
        return LastNameS;
    }

    public void setLastNameS(String lastNameS) {
        LastNameS = lastNameS;
    }

    public String getAdressS() {
        return adressS;
    }

    public void setAdressS(String adressS) {
        this.adressS = adressS;
    }

    public String getUserNS() {
        return userNS;
    }

    public void setUserNS(String userNS) {
        this.userNS = userNS;
    }

    public String getEmailS() {
        return emailS;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

    public int getPhoneS() {
        return phoneS;
    }

    public void setPhoneS(int phoneS) {
        this.phoneS = phoneS;
    }

    public int getPassS() {
        return passS;
    }

    public void setPassS(int passS) {
        this.passS = passS;
    }

    public int getPassConfiormS() {
        return passConfiormS;
    }

    public void setPassConfiormS(int passConfiormS) {
        this.passConfiormS = passConfiormS;
    }
}
