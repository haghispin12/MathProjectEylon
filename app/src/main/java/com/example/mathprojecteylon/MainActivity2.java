package com.example.mathprojecteylon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
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




            btMainEtgar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createClickListeneretgar();
                }

            });
            btMainKefel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createClickListenerkefel();
                }
            });
            btMainLuch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createClickListenerluch();
                }
            });
            btMainBdica.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    createClickListenerBdica();
                }
            });
        }
        public void createClickListeneretgar() {
            Random random = new Random();
            int num1 = random.nextInt(25);
            int num2 = random.nextInt(50) + 5;
            String s=num1+"";
            String st=num2+"";
            tvMain1.setText(s);
            tvMain2.setText(st);
            result=num1*num2;

        }

        public void createClickListenerkefel() {
            Random random = new Random();
            int num1 = random.nextInt(20);
            int num2 = random.nextInt(10);
            String s=num1+"";
            String st=num2+"";
            tvMain1.setText(s);
            tvMain2.setText(st);
            result=num1*num2;
        }

        public void createClickListenerluch() {
            Random random = new Random();
            int num1 = random.nextInt(10);
            int num2 = random.nextInt(10);
            String s=num1+"";
            String st=num2+"";
            tvMain1.setText(s);
            tvMain2.setText(st);
            result=num1*num2;
        }
        public void createClickListenerBdica(){
            String s=etMainTsuva.getText().toString();
            String str=result+"";
            if(s.equals(str)) {
                Toast.makeText(MainActivity2.this,"success",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity2.this,"wrong",Toast.LENGTH_SHORT).show();
            }
        }
}