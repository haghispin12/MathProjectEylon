package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;

public class MainActivityManager extends AppCompatActivity {

    private Button btnOrderDetails;
    private Button btnUpdateStatus;
    private Button btnLogoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);

        btnOrderDetails = findViewById(R.id.btnOrderDetails);
        btnUpdateStatus = findViewById(R.id.btnUpdateStatus);
        btnLogoutManager = findViewById(R.id.btnLogoutManager);

        // מעבר למסך פרטי ההזמנות
        btnOrderDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityManager.this, OrderDetailsActivity.class);
            startActivity(intent);
        });

        // מעבר למסך עדכון סטטוסים
        btnUpdateStatus.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityManager.this, AdminActivity.class);
            startActivity(intent);
        });

        // כפתור יציאה — מאפס את SharedPreferences וחוזר למסך הפתיחה
        btnLogoutManager.setOnClickListener(v -> {
            getSharedPreferences("app", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isManager", false)
                    .apply();
            Intent intent = new Intent(MainActivityManager.this, launcher2.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}