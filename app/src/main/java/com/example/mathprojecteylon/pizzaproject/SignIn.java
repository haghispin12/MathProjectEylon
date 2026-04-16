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

    public void init() {
        registerS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailS.getText().toString();
                String password = passS.getText().toString();
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
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