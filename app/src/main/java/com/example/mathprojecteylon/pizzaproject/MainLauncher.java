package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        int n =10;

        new Handler().postDelayed(new Runnable() {//מסך פתיחה מעביר למסך הבא
            @Override
            public void run() {
                Intent intent = new Intent(MainLauncher.this, launcher2.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // 2000 = 2 שניות
    }
}