package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;

public class PizzaDetailsActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView tvPizzaTitle;
    private ImageView ivPizzaImage;
    private TextView tvPizzaName;
    private TextView tvPizzaPrice;
    private RadioGroup rgSize;
    private RadioButton rbSmall;
    private RadioButton rbMedium;
    private RadioButton rbLarge;
    private RadioButton rbXLarge;
    private CheckBox cbTomato;
    private CheckBox cbCorn;
    private CheckBox cbOlives;
    private CheckBox cbMushrooms;
    private CheckBox cbOnion;
    private CheckBox cbPepper;
    private CheckBox cbExtraCheese;
    private CheckBox cbPineapple;
    private CheckBox cbTuna;
    private TextView tvTotalPrice;
    private Button btnAddToCart;
    private String pizzaName;
    private String pizzaImage;
    private int pizzaPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);
        btnBack = findViewById(R.id.btnBack);
        tvPizzaTitle = findViewById(R.id.tvPizzaTitle);
        ivPizzaImage = findViewById(R.id.ivPizzaImage);
        tvPizzaName = findViewById(R.id.tvPizzaName);
        tvPizzaPrice = findViewById(R.id.tvPizzaPrice);
        rgSize = findViewById(R.id.rgSize);
        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);
        rbXLarge = findViewById(R.id.rbXLarge);
        cbTomato = findViewById(R.id.cbTomato);
        cbCorn = findViewById(R.id.cbCorn);
        cbOlives = findViewById(R.id.cbOlives);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        cbOnion = findViewById(R.id.cbOnion);
        cbPepper = findViewById(R.id.cbPepper);
        cbExtraCheese = findViewById(R.id.cbExtraCheese);
        cbPineapple = findViewById(R.id.cbPineapple);
        cbTuna = findViewById(R.id.cbTuna);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        pizzaName = getIntent().getStringExtra("pizzaName");
        pizzaImage = getIntent().getStringExtra("pizzaImage");
        pizzaPrice = getIntent().getIntExtra("pizzaPrice");
        init();

        };
     public void init(){
         btnBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(PizzaDetailsActivity.this, MainActivityPizza.class);
                 startActivity(intent);
             }
         });
         rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 String S=pizzaPrice-10;

                 if (checkedId == R.id.rbSmall) {
                     tvTotalPrice.setText("סך הכול לתשלום:"+);
                     // בחר S
                 } else if (checkedId == R.id.rbMedium) {
                     // בחר M
                 } else if (checkedId == R.id.rbLarge) {
                     // בחר L
                 } else if (checkedId == R.id.rbXLarge) {
                     // בחר XL
                 }

             }
         });

     }
    }
