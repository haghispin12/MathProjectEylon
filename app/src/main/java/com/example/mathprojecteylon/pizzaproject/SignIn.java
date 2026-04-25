package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import com.google.android.gms.tasks.Task;

/**
 * מסך ההרשמה של האפליקציה.
 * מאפשר ללקוח חדש ליצור חשבון עם אימייל וסיסמא.
 * לפני שליחה ל-Firebase — בודק שכל השדות מולאו.
 * לאחר הרשמה מוצלחת — נשמרים פרטי המשתמש ב-Firestore.
 */
public class SignIn extends AppCompatActivity {

    private EditText FirstNameS;
    private EditText LastNameS;
    private EditText adressS;
    private EditText emailS;
    private EditText phoneS;
    private EditText passS;
    private EditText passConfiormS;
    private Button registerS;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        FirstNameS = findViewById(R.id.etFirstName);
        LastNameS = findViewById(R.id.etLastName);
        adressS = findViewById(R.id.etAddress);
        emailS = findViewById(R.id.etEmail);
        phoneS = findViewById(R.id.etPhone);
        passS = findViewById(R.id.etPassword);
        passConfiormS = findViewById(R.id.etPasswordConfirm);
        registerS = findViewById(R.id.btnRegister);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        init();
    }

    /**
     * בודק שכל השדות מולאו לפני הרשמה.
     * מחזיר true אם הכל תקין, false אם חסר שדה.
     * מציג Toast עם שם השדה החסר.
     */
    private boolean validateFields() {
        if (FirstNameS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא שם פרטי", Toast.LENGTH_SHORT).show();
            FirstNameS.requestFocus();
            return false;
        }
        if (LastNameS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא שם משפחה", Toast.LENGTH_SHORT).show();
            LastNameS.requestFocus();
            return false;
        }
        if (emailS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא אימייל", Toast.LENGTH_SHORT).show();
            emailS.requestFocus();
            return false;
        }
        if (adressS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא כתובת", Toast.LENGTH_SHORT).show();
            adressS.requestFocus();
            return false;
        }
        if (phoneS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא טלפון", Toast.LENGTH_SHORT).show();
            phoneS.requestFocus();
            return false;
        }
        if (passS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא למלא סיסמא", Toast.LENGTH_SHORT).show();
            passS.requestFocus();
            return false;
        }
        if (passConfiormS.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "נא לאשר סיסמא", Toast.LENGTH_SHORT).show();
            passConfiormS.requestFocus();
            return false;
        }
        if (!passS.getText().toString().equals(passConfiormS.getText().toString())) {
            Toast.makeText(this, "הסיסמאות לא תואמות", Toast.LENGTH_SHORT).show();
            passConfiormS.requestFocus();
            return false;
        }
        return true;
    }

    public void init() {
        registerS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // בדיקת שדות חובה לפני שליחה ל-Firebase
                if (!validateFields()) return;

                String email = emailS.getText().toString();
                String password = passS.getText().toString();

                // שלב 1 — יוצרים משתמש ב-Firebase Authentication
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // שלב 2 — שמירת פרטי המשתמש ב-Firestore
                                    Map<String, Object> userData = new HashMap<>();
                                    userData.put("firstName", FirstNameS.getText().toString());
                                    userData.put("lastName", LastNameS.getText().toString());
                                    userData.put("email", emailS.getText().toString());
                                    userData.put("address", adressS.getText().toString());
                                    userData.put("phone", phoneS.getText().toString());

                                    db.collection("buyer details")
                                            .document(emailS.getText().toString())
                                            .set(userData)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(SignIn.this, "ההרשמה הצליחה!", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(SignIn.this, logIn.class);
                                                    startActivity(intent);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                } else {
                                    Toast.makeText(SignIn.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}