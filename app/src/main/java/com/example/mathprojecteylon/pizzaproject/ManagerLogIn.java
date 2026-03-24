package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;

public class ManagerLogIn extends AppCompatActivity {
    private EditText userName;
    private EditText password;
    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_log_in);
        userName=findViewById(R.id.etAdminUsername);
        password=findViewById(R.id.etAdminPassword);
        enter=findViewById(R.id.btnAdminLogin);
        init();

        }
        public void init(){
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ps=password.getText().toString();
            if (userName.getText().toString().equals("manager")){
            if (ps.equals(5230)){
                Intent intent = new Intent(ManagerLogIn.this, MainActivityManager.class);
                intent.putExtra("name","Manager");
                startActivity(intent);
            }
            }
            else {
                Toast.makeText(ManagerLogIn.this, "כניסה שגויה נסה שנית", Toast.LENGTH_SHORT).show();

            }




            }
        });
        }
    }
