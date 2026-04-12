package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class logIn extends AppCompatActivity {
    private EditText userN;
    private EditText Email;
    private EditText pass;
    private Button enter;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Email = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPassword);
        enter = findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();
        init();
        ;
    }

    public void init() {
   enter.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
       String email =Email.getText().toString();
       String password=pass.getText().toString();
       auth.signInWithEmailAndPassword(email,password)
               .addOnCompleteListener(logIn.this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           Buyer.currentBuyer = new Buyer("", "", email, 0, 0, "", 0);
                           Toast.makeText(logIn.this, "ברוך הבא", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(logIn.this, MainActivityPizza.class);
                           startActivity(intent);

                       }
                       else {
                           Toast.makeText(logIn.this, "סיסמה שגויה נסה שוב", Toast.LENGTH_SHORT).show();

                       }
                   }
               });




       }
   });
    }
}
