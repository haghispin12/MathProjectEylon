package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
    private CheckBox cbBlacklOlive;
    private CheckBox cbPineapple;
    private CheckBox cbTuna;
    private TextView tvTotalPrice;
    private Button btnAddToCart;
    private String pizzaName;
    private String pizzaImage;
    private int basePrice;
    private int totalPrice;
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
        cbBlacklOlive =findViewById(R.id.cbBlackOlive);
        cbPineapple = findViewById(R.id.cbPineapple);
        cbTuna = findViewById(R.id.cbTuna);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        pizzaName = getIntent().getStringExtra("pizzaName");
        pizzaImage = getIntent().getStringExtra("pizzaImage");
        basePrice = Integer.parseInt("pizzaPrice");
        totalPrice=basePrice;
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

                 if (checkedId == R.id.rbSmall) {
                     totalPrice=basePrice-10;
                     tvTotalPrice.setText(totalPrice+"סך הכול לתשלום:");
                     // בחר S
                 } else if (checkedId == R.id.rbMedium) {
                     totalPrice=basePrice;
                     tvTotalPrice.setText(totalPrice+"סך הכול לתשלום:");
                     // בחר M
                 } else if (checkedId == R.id.rbLarge) {
                     totalPrice=basePrice+10;
                     tvTotalPrice.setText(totalPrice+"סך הכול לתשלום:");
                     // בחר L
                 } else if (checkedId == R.id.rbXLarge) {
                     totalPrice=basePrice+20;
                     tvTotalPrice.setText(totalPrice+"סך הכול לתשלום:");
                     // בחר XL
                 }

             }
         });
         cbTomato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbCorn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbOlives.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbPepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbOnion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbPineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+6;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbTuna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+8;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });
         cbBlaclOlive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     totalPrice=totalPrice+5;
                     // נסמן — הוסף למחיר
                 } else {
                     totalPrice=totalPrice;
                     // בוטל — הורד מהמחיר
                 }
             }
         });



     }
    }
