package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;

public class MainActivityManager extends AppCompatActivity {

    private Button btnOrderDetails;
    private Button btnUpdateStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_manager);
        btnOrderDetails = findViewById(R.id.btnOrderDetails);
        btnUpdateStatus = findViewById(R.id.btnUpdateStatus);

        btnOrderDetails.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityManager.this, OrderDetailsActivity.class);
            startActivity(intent);
        });

        btnUpdateStatus.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityManager.this, AdminActivity.class);
            startActivity(intent);
        });
    }
}