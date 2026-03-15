package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mathprojecteylon.R;

public class launcher extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {//מסך פתיחה מעביר למסך הבא
            @Override
            public void run() {
                Intent intent = new Intent(launcher.this, launcher2.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // 2000 = 2 שניות
    }
}