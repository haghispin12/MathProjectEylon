package com.example.mathprojecteylon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private Button Sub;
    private EditText Enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       init();

    }
    public void init (){
     Sub=findViewById(R.id.Sub);
     Enter=findViewById(R.id.Enter);
     Intent intent=new Intent(this, MainActivity2.class);
     intent.putExtra("Name",Enter.getText().toString());
    }
  public void onCreateSub(){
        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
  }


}