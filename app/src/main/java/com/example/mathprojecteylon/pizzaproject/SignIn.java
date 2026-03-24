package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class SignIn extends AppCompatActivity {

    private EditText FirstNameS;
    private EditText LastNameS;
    private EditText adressS;
    private EditText emailS;
    private EditText phoneS;
    private EditText passS;
    private EditText passConfiormS;

    private Button registerS;


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
        init();

    }

    public void init() {
         registerS.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email = emailS.getText().toString();
                 SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                 editor.putString("email", emailS.getText().toString());
                 editor.putInt("password", Integer.parseInt(passS.getText().toString()));
                 editor.apply();
                 String savedEmail = sharedPreferences.getString("email", "");
                 if (savedEmail.equals(email)) {
                     Toast.makeText(SignIn.this, "שם המשתמש כבר קיים!", Toast.LENGTH_SHORT).show();
                 } else {
                     // אימייל חדש — אפשר להירשם


                     // יוצרים משתמש חדש ומוסיפים לרשימה
                     User newUser = new User(FirstNameS.getText().toString(),
                             LastNameS.getText().toString(),
                             emailS.getText().toString(),
                             Integer.parseInt(passS.getText().toString()),
                             Integer.parseInt(passConfiormS.getText().toString()));

                     User.users.add(newUser);
                     Intent intent = new Intent(SignIn.this, logIn.class);
                     startActivity(intent);
                 }
             }
     });

             }


}