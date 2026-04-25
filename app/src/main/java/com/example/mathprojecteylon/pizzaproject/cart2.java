package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

/**
 * מסך עגלת הקניות.
 * מציג את הפיצות שהלקוח בחר ברשימה עם סכום כולל מתעדכן.
 * בלחיצה על תשלום — ההזמנה נשמרת ב-Firestore ועובר למסך ההזמנות.
 *
 * משתמש ב-RecyclerView עם cartAdapter להצגת הפיצות.
 * ה-RecyclerView כאן עובד עם OnCartChangedListener —
 * כשמוחקים פיצה מהרשימה, ה-Adapter מודיע ל-cart2 לעדכן את הסכום.
 */
public class cart2 extends AppCompatActivity {

    // כפתור אישור תשלום
    private Button pay;

    // כפתור חזרה לתפריט
    private Button back;

    // אובייקט גישה ל-Firestore
    private FirebaseFirestore db;

    // תצוגת הסכום הכולל
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        // יצירת ה-RecyclerView להצגת הפיצות בעגלה
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);

        // יצירת ה-Adapter — מקבל את רשימת הפיצות מ-Buyer.currentBuyer
        // OnCartChangedListener — כשמוחקים פיצה, ה-Adapter קורא ל-onCartChanged
        // שמפעיל את updateTotal() ומעדכן את הסכום
        cartAdapter adapter = new cartAdapter(Buyer.currentBuyer.getCart(), new cartAdapter.OnCartChangedListener() {
            @Override
            public void onCartChanged() {
                // נקרא אוטומטית מה-Adapter כשמוחקים פיצה
                updateTotal();
            }
        });

        // LinearLayoutManager — מציג את הפריטים אחד מתחת לשני
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        pay = findViewById(R.id.btnCheckout);
        back = findViewById(R.id.btnBack);
        totalPrice = findViewById(R.id.tvTotalPrice);
        db = FirebaseFirestore.getInstance();

        init();

        // עדכון הסכום הכולל בטעינת המסך
        updateTotal();
    }

    /**
     * מחשב את הסכום הכולל של כל הפיצות בעגלה ומציג אותו.
     * נקראת גם בטעינת המסך וגם כשמוחקים פיצה.
     */
    public void updateTotal() {
        int total = 0;
        for (Pizza pizza : Buyer.currentBuyer.getCart()) {
            total += pizza.getPrice();
        }
        totalPrice.setText("₪" + total);
    }

    /**
     * מגדיר את מאזיני הלחיצה על הכפתורים
     */
    public void init() {

        // כפתור תשלום — שומר את ההזמנה ב-Firestore
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // חישוב הסכום הכולל
                int total = 0;
                for (int i = 0; i < Buyer.currentBuyer.getCart().size(); i++) {
                    total += Buyer.currentBuyer.getCart().get(i).getPrice();
                }

                // בניית המפה לשמירה ב-Firestore
                // Map משמש כי Firestore שומר נתונים כצמדי מפתח-ערך
                Map<String, Object> orderData = new HashMap<>();
                orderData.put("email", Buyer.currentBuyer.getEmailS()); // לזיהוי הקונה
                orderData.put("cart", Buyer.currentBuyer.getCart());     // הפיצות שהוזמנו
                orderData.put("totalPrice", total);                       // סכום כולל
                orderData.put("status", "מחכה לאישור המנהל");            // סטטוס התחלתי
                orderData.put("estimatedTime", 0);                        // זמן הכנה — המנהל יגדיר אחר כך

                // שמירת ההזמנה ב-Firestore
                // document() ללא פרמטר — יוצר מזהה אוטומטי להזמנה
                db.collection("orders").document()
                        .set(orderData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                // ניקוי העגלה אחרי הזמנה מוצלחת
                                Buyer.currentBuyer.getCart().clear();

                                Toast.makeText(cart2.this, "ההזמנה נשמרה!", Toast.LENGTH_SHORT).show();

                                // מעבר למסך ההזמנות
                                // FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TASK —
                                // מוחק את כל המסכים הקודמים כדי שלא יוכל לחזור לעגלה
                                Intent intent = new Intent(cart2.this, OrdersActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // שגיאה בשמירה — מציג את הודעת השגיאה
                                Toast.makeText(cart2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        // כפתור חזרה לתפריט הפיצות
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart2.this, MainActivityPizza.class);
                startActivity(intent);
            }
        });
    }
}