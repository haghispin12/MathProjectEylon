package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;
import com.example.mathprojecteylon.mathproject.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
        auth=FirebaseAuth.getInstance();
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
                                     Toast.makeText(SignIn.this, "Registiration success.",
                                             Toast.LENGTH_SHORT).show();
                                     Intent intent = new Intent(SignIn.this, logIn.class);
                                     startActivity(intent);
                                 } else {
                                     Toast.makeText(SignIn.this, "registartion failed.",
                                             Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });


             }


     });

             }


}