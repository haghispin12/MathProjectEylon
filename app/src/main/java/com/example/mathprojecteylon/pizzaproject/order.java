package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

/**
 * מחלקה המייצגת הזמנה אחת באפליקציה.
 * כשלקוח מאשר הזמנה ב-cart2 — נוצר אובייקט מהמחלקה הזאת ונשמר ב-Firestore.
 * המנהל יכול לראות את ההזמנות האלה ולעדכן את הסטטוס שלהן.
 */
public class order {

    // אימייל הקונה — משמש לזיהוי מי ביצע את ההזמנה
    private String email;

    // רשימת הפיצות שהוזמנו
    private ArrayList<Pizza> cart;

    // הסכום הכולל של ההזמנה בשקלים
    private int totalPrice;

    // סטטוס ההזמנה — מחכה לאישור המנהל / בהכנה / מוכן
    private String status;

    /**
     * קונסטרקטור ריק — נדרש על ידי Firebase Firestore
     * כדי לשחזר אובייקט מהמסד נתונים
     */
    public order() {}

    /**
     * קונסטרקטור ראשי — מאתחל הזמנה חדשה.
     * הסטטוס ברירת מחדל הוא "ממתין" — כי כשנוצרת הזמנה היא עדיין ממתינה לאישור
     */
    public order(String email, ArrayList<Pizza> cart, int totalPrice) {
        this.email = email;
        this.cart = cart;
        this.totalPrice = totalPrice;
        this.status = "ממתין";
    }

    // ===== פעולות Get & Set =====

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public ArrayList<Pizza> getCart() { return cart; }
    public void setCart(ArrayList<Pizza> cart) { this.cart = cart; }

    // הוספת פיצה לרשימת ההזמנה
    public void addToCart(Pizza pizza) {
        cart.add(pizza);
    }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}