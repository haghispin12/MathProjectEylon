package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

/**
 * מחלקת הקונה — יורשת מ-User.
 * מוסיפה פרטים ייחודיים לקונה: כתובת, טלפון ועגלת קניות.
 *
 * ירושה (extends User) — Buyer מקבל את כל תכונות User (שם, אימייל, סיסמא)
 * ומוסיף עליהן תכונות נוספות שרלוונטיות רק לקונה.
 *
 * currentBuyer הוא static — כלומר הוא שייך למחלקה ולא לאובייקט ספציפי.
 * יש רק עותק אחד שלו בכל האפליקציה — נגיש מכל מסך ב-Buyer.currentBuyer.
 * זה חוסך את הצורך להעביר את הקונה בין מסכים דרך Intent.
 */
public class Buyer extends User {

    // כתובת המשלוח של הקונה
    private String adress;

    // מספר טלפון של הקונה
    private int phoneNum;

    // עגלת הקניות — רשימת הפיצות שהקונה בחר
    private ArrayList<Pizza> cart;

    /**
     * משתנה סטטי המייצג את הקונה המחובר כרגע.
     * static — נגיש מכל מסך באפליקציה ללא צורך בהעברה דרך Intent.
     * מאותחל בעת התחברות ב-logIn ומתעדכן ב-launcher2.
     */
    public static Buyer currentBuyer;

    /**
     * קונסטרקטור ראשי — מאתחל את כל פרטי הקונה.
     * קורא ל-super() כדי לאתחל את תכונות User (שם, אימייל, סיסמא).
     * מאתחל את העגלה כ-ArrayList ריק — לא null — כדי למנוע קריסות.
     */
    public Buyer(String firstName, String lastName, String email, int pass, int passConiform, String adress, int phoneNum) {
        super(firstName, lastName, email, pass, passConiform);
        this.adress = adress;
        this.phoneNum = phoneNum;
        this.cart = new ArrayList<Pizza>();
    }

    /**
     * קונסטרקטור ריק — נדרש על ידי Firebase Firestore
     * כדי לשחזר אובייקט מהמסד נתונים
     */
    public Buyer() {}

    // ===== פעולות Get & Set =====

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

    /**
     * מוסיף פיצה לעגלת הקניות.
     * נקראת מ-MainActivityPizza ומ-PizzaDetailsActivity.
     */
    public void addToCart(Pizza pizza) {
        cart.add(pizza);
    }
}