package com.example.mathprojecteylon.pizzaproject;

import android.widget.EditText;

import java.util.ArrayList;

public class User {
    private String FirstNameS;
    private String LastNameS;
    private String emailS;
    private int passS;
    private int passConfiormS;

    public User(String FirstNameS,String LastNameS,String emailS
            ,int passS,int passConfiormS){
        this.FirstNameS=FirstNameS;
        this.LastNameS=LastNameS;
        this.emailS=emailS;
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



    public String getEmailS() {
        return emailS;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
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
