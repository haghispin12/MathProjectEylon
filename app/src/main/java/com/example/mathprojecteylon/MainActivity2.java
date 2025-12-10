package com.example.mathprojecteylon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.utilities.Score;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    private Button btMainEtgar;
    private Button btMainKefel;
    private Button btMainLuch;
    private TextView tvMain1;
    private TextView tvMain2;
    private EditText etMainTsuva;
    private Button btMainBdica;
    private Button btMainSave;
    private Button btMAinShow;
    private int result;
    private Exercise ex;
    private Inter inter;
    private int score;
    private boolean Etg=false;
    private boolean lch=false;
    private boolean Kfl=false;
    private User us;
    private Button Rate;


    ActivityResultLauncher<Intent>activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
        int myRate=result.getData().getIntExtra("Rate",-1);
        Toast.makeText(MainActivity2.this,myRate+"",Toast.LENGTH_SHORT).show();
        }
    });



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
         ex=new Exercise(inter);
         Intent intent=getIntent();
        String y=intent.getStringExtra("name");
         us=new User(y);
         us.setScore(score);
    }


        public  void init() {
            btMainEtgar = findViewById(R.id.btMainEtgar);
            btMainKefel = findViewById(R.id.btMainKefel);
            btMainLuch = findViewById(R.id.btMainLuch);
            tvMain1 = findViewById(R.id.tvMain1);
            tvMain2 = findViewById(R.id.tvMain2);
            etMainTsuva = findViewById(R.id.etMainTsuva);
            btMainBdica = findViewById(R.id.btMainBdica);
            btMainSave = findViewById(R.id.btMainSave);
            btMAinShow = findViewById(R.id.btMAinShow);
            Rate=findViewById(R.id.btRate);

            inter = new Inter() {
                @Override
                public void showNumber(int num1, int num2) {
                 tvMain1.setText(num1+"");
                 tvMain2.setText(num2+"");
                }
            };




            btMainEtgar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ex.etgar();
                    Etg=true;
                    //createClickListeneretgar();
                }

            });
            btMainKefel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ex.kefel();
                    Kfl=true;
                    //createClickListenerkefel();
                }
            });
            btMainLuch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ex.luch();
                    lch=true;
                    //createClickListenerluch();
                }

            });
            btMainBdica.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    createClickListenerBdica();
                }
            });
            Rate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent= new Intent(MainActivity2.this, Rate.class);
                activityResultLauncher.launch(intent);
                }
            });
        }


        public void createClickListenerBdica(){
        String s=etMainTsuva.getText()+"";
            boolean x= ex.isCorrect(s);
            etMainTsuva.setText("");
            if(x) {
                Toast.makeText(MainActivity2.this,"success",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity2.this,"wrong",Toast.LENGTH_SHORT).show();
            }
            if (x==true){
            if (Etg==true){
            score=score+20;
            }
            if (lch==true){
            score=score+5;
                }
            if (Kfl==true){
            score=score+10;
                }
            }
            Etg=false;
            lch=false;
            Kfl=false;
        }






}