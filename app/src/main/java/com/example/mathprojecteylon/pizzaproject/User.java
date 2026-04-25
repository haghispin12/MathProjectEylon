package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

/**
 * מחלקת בסיס המייצגת משתמש באפליקציה.
 * מחלקת Buyer יורשת ממחלקה זו.
 */
public class User {

    // שם פרטי של המשתמש
    private String FirstNameS;

    // שם משפחה של המשתמש
    private String LastNameS;

    // אימייל המשתמש — משמש גם כמזהה ב-Firebase
    private String emailS;

    // סיסמא של המשתמש
    private int passS;

    // אישור סיסמא — חייב להיות זהה ל-passS
    private int passConfiormS;

    /**
     * קונסטרקטור ראשי — מאתחל את כל פרטי המשתמש
     */
    public User(String FirstNameS, String LastNameS, String emailS, int passS, int passConfiormS) {
        this.FirstNameS = FirstNameS;
        this.LastNameS = LastNameS;
        this.emailS = emailS;
        this.passS = passS;
        this.passConfiormS = passConfiormS;
    }

    /**
     * קונסטרקטור ריק — נדרש על ידי Firebase Firestore
     * כדי לשחזר אובייקט מהמסד נתונים
     */
    public User() {}

    // ===== פעולות Get & Set =====

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