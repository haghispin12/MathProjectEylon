package com.example.mathprojecteylon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Rate extends AppCompatActivity {
    private SeekBar seek;
    private Button rate;



public void init() { //מבצעת חיבור בין הXML לקוד בזה שמסנכרנת בין מה שהכתפורים עושים למה שכתוב עליהם
    rate=findViewById(R.id.btRate);
    seek = findViewById(R.id.seek);
}

    @Override
    protected void onCreate (Bundle savedInstanceState){//פעולת onCreate  מייצרת את כול הדברים שקורים עם פתיחת מסך הRate

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        init();

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rate.setOnClickListener(new View.OnClickListener() {//פותח clickListener ומפרט מה שקורה ברגע שלוחצים על הפעולה
            @Override
            public void onClick(View view) {
                Intent inn = new Intent();
                inn.putExtra("Rate", seek.getProgress());
                setResult(RESULT_OK, inn);
                finish();
            }
        });
    }






}