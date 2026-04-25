package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class launcher2 extends AppCompatActivity {

    private Button btnManagerEnter;
    private Button btnBuyerLogin;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher2);

        btnManagerEnter = findViewById(R.id.btnAdmin);
        btnBuyerLogin = findViewById(R.id.btnCustomer);
        btnSignIn = findViewById(R.id.btnRegister);

        // בדיקה 1 — האם המנהל נכנס בפעם הקודמת
        // SharedPreferences שומר את זה בין פתיחות של האפליקציה
        boolean isManager = getSharedPreferences("app", MODE_PRIVATE)
                .getBoolean("isManager", false);
        if (isManager) {
            Intent intent = new Intent(launcher2.this, MainActivityManager.class);
            startActivity(intent);
            finish();
            return;
        }

        // בדיקה 2 — האם לקוח כבר מחובר ב-Firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            btnManagerEnter.setVisibility(View.GONE);
            btnBuyerLogin.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);

            String email = auth.getCurrentUser().getEmail();
            Buyer.currentBuyer = new Buyer("", "", email, 0, 0, "", 0);

            FirebaseFirestore.getInstance().collection("buyer details").document(email)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        String firstName = documentSnapshot.exists() ?
                                documentSnapshot.getString("firstName") : "";
                        Toast.makeText(launcher2.this, "ברוך הבא חזרה " + firstName + "!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(launcher2.this, MainActivityPizza.class);
                        startActivity(intent);
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Intent intent = new Intent(launcher2.this, MainActivityPizza.class);
                        startActivity(intent);
                        finish();
                    });
            return;
        }

        init();
    }

    public void init() {
        btnManagerEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(launcher2.this, ManagerLogIn.class);
                startActivity(intent);
            }
        });
        btnBuyerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(launcher2.this, logIn.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(launcher2.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}