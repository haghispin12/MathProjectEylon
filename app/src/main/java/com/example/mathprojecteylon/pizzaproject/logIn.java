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

public class logIn extends AppCompatActivity {
    private EditText userN;
    private EditText email;
    private EditText pass;
    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        userN = findViewById(R.id.etUsername);
        email = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPassword);
        enter = findViewById(R.id.btnLogin);
        init();
        ;
    }

    public void init() {
   enter.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
       String emaiL =email.getText().toString();
       String password=pass.getText().toString();
         for(User u:User.users ){
             String t=u.getPassS()+"";
             if(u.getEmailS().equals(emaiL)&&t.equals(password)){
                 Buyer.currentBuyer = new Buyer(u.getFirstNameS(),
                         u.getLastNameS(),
                         u.getEmailS(),
                         u.getPassS(),
                         u.getPassConfiormS(),
                         "",    // כתובת
                         0 );     // טלפון
                 Intent intent = new Intent(logIn.this, MainActivityPizza.class);
                 startActivity(intent);
             }
             else {
                 Toast.makeText(logIn.this, "סיסמה שגויה נסה שוב", Toast.LENGTH_SHORT).show();
             }
}
       }
   });
    }
}
