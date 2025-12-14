package com.example.mathprojecteylon;

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

public class LoginActivity extends AppCompatActivity {
    private Button Sub;
    private EditText Enter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       init();
       onCreateSub();
       getName();
        SharedPreferences sh=getSharedPreferences("MyShared",MODE_PRIVATE);
        String s1=sh.getString("name","");
       Enter.setText(s1);

    }
    public void init (){
     Sub=findViewById(R.id.Sub);
     Enter=findViewById(R.id.Enter);
    }
  public void onCreateSub(){
        Sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =getSharedPreferences("MyShared",MODE_PRIVATE);
                SharedPreferences.Editor myEdit=sharedPreferences.edit();
                myEdit.putString("name",Enter.getText().toString());
                myEdit.apply();



                Intent intent=new Intent(LoginActivity.this, MainActivity2.class);
                intent.putExtra("Name",Enter.getText().toString());
                startActivity(intent);
            }
        });
  }
  public String getName(){
  String x=Enter+"";
  return x;
  }


}