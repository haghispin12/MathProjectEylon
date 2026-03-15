package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;

public class SignIn extends AppCompatActivity {

    private EditText FirstNameS;
    private EditText LastNameS;
    private EditText adressS;
    private EditText userNS;
    private EditText emailS;
    private EditText phoneS;
    private EditText passS;
    private EditText passConfiormS;

    private Button registerS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        FirstNameS.findViewById(R.id.etFirstName);
        LastNameS.findViewById(R.id.etLastName);
        adressS.findViewById(R.id.etAddress);
        userNS.findViewById(R.id.etUsername);
        emailS.findViewById(R.id.etEmail);
        phoneS.findViewById(R.id.etPhone);
        passS.findViewById(R.id.etPassword);
        passConfiormS.findViewById(R.id.etPasswordConfirm);
        registerS.findViewById(R.id.btnRegister);
        init();

    }

    public void init() {


    }
}