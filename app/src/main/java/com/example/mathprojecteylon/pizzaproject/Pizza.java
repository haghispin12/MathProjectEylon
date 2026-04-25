package com.example.mathprojecteylon.pizzaproject;

import java.util.ArrayList;

/**
 * מחלקה המייצגת פיצה אחת באפליקציה.
 * נוצר אובייקט מהמחלקה הזאת כשהלקוח בוחר פיצה ומוסיף לעגלה.
 */
public class Pizza {

    // המחיר הסופי של הפיצה — כולל גודל ותוספות
    private int price;

    // שם הפיצה — למשל "מוצרלה", "פיצה יוונית"
    private String name;

    // גודל הפיצה — S, M, L או XL
    private String size;

    // רשימת התוספות שנבחרו — למשל ["tomato", "corn", "olives"]
    private ArrayList<String> extras;

    // שם קובץ התמונה ב-drawable — משמש להצגת התמונה ברשימה
    private String imageName;

    /**
     * קונסטרקטור ראשי — מאתחל את כל פרטי הפיצה
     */
    public Pizza(int price, String name, String size, ArrayList<String> extras, String imageName) {
        this.price = price;
        this.name = name;
        this.size = size;
        this.extras = extras;
        this.imageName = imageName;
    }

    /**
     * קונסטרקטור ריק — נדרש על ידי Firebase Firestore
     * כדי לשחזר אובייקט מהמסד נתונים
     */
    public Pizza() {}

    // ===== פעולות Get & Set =====

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}