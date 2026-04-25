package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * מסך הפתיחה של האפליקציה — Splash Screen.
 * מוצג למשך 2 שניות בעת פתיחת האפליקציה.
 * לאחר 2 שניות עובר אוטומטית למסך הבחירה (launcher2).
 */
public class MainLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Handler מאפשר להריץ קוד אחרי השהייה מסוימת
        // postDelayed מקבל שני פרמטרים:
        // 1. Runnable — הקוד שירוץ אחרי ההשהייה
        // 2. זמן השהייה במילישניות (2000 = 2 שניות)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // אחרי 2 שניות — מעבר למסך הבחירה בין לקוח למנהל
                Intent intent = new Intent(MainLauncher.this, launcher2.class);
                startActivity(intent);

                // finish() סוגר את מסך הפתיחה כדי שהמשתמש לא יוכל לחזור אליו
                finish();
            }
        }, 2000);
    }
}