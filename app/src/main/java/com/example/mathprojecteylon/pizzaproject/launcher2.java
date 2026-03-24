package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mathprojecteylon.R;

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
        init();

        }
        public void init(){
    btnManagerEnter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(launcher2.this, MainActivityPizza.class);
            startActivity(intent);
        }
    });
    btnBuyerLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(launcher2.this,logIn.class);
            startActivity(intent);
        }
    });
    btnSignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intnet=new Intent(launcher2.this, SignIn.class);
            startActivity(intnet);
        }
    });
    }
    }
