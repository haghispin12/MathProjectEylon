package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mathprojecteylon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * מסך ההתחברות של הלקוח.
 * הלקוח מכניס אימייל וסיסמא — Firebase Auth מאמת אותם מול הענן.
 * לאחר התחברות מוצלחת — נוצר Buyer.currentBuyer ועובר לתפריט הפיצות.
 */
public class logIn extends AppCompatActivity {

    // שדות קלט
    private EditText userN;
    private EditText Email;
    private EditText pass;
    private Button enter;

    // אובייקט לניהול אימות משתמשים עם Firebase
    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // חיבור המשתנים לשדות ב-XML
        Email = findViewById(R.id.etEmail);
        pass = findViewById(R.id.etPassword);
        enter = findViewById(R.id.btnLogin);

        // אתחול Firebase Auth
        auth = FirebaseAuth.getInstance();

        init();
    }

    /**
     * מגדיר את מאזין הלחיצה על כפתור ההתחברות
     */
    public void init() {
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = pass.getText().toString();

                // שליחת בקשת התחברות ל-Firebase Auth
                // Firebase בודק את האימייל והסיסמא מול הענן
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(logIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // התחברות הצליחה — יוצרים את הקונה המחובר
                                    // Buyer.currentBuyer הוא static — נגיש מכל מסך באפליקציה
                                    Buyer.currentBuyer = new Buyer("", "", email, 0, 0, "", 0);

                                    Toast.makeText(logIn.this, "ברוך הבא", Toast.LENGTH_SHORT).show();

                                    // מעבר לתפריט הפיצות
                                    Intent intent = new Intent(logIn.this, MainActivityPizza.class);
                                    startActivity(intent);
                                } else {
                                    // התחברות נכשלה — אימייל או סיסמא שגויים
                                    Toast.makeText(logIn.this, "סיסמה שגויה נסה שוב", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    /**
     * פונקציה שנכתבה לצורך בדיקה — לא בשימוש כרגע
     * שמירת פרטי משתמש לאוסף cities (נותר מגרסה קודמת)
     */
    public void addUser(String email, String password) {
        // פונקציה זו לא פעילה — הוחלפה בשמירה באוסף users ב-SignIn
    }
}