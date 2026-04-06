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

import java.util.ArrayList;

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
    private int sizePrice;
    private ArrayList<String> extras = new ArrayList<>();
    private String size;
    private int extrasPrice;
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
        basePrice = Integer.parseInt(getIntent().getStringExtra("pizzaPrice"));
        size="M";
        sizePrice=basePrice;
        int id = getResources().getIdentifier(pizzaImage, "drawable", getPackageName());
        ivPizzaImage.setImageResource(id);

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
                     sizePrice=basePrice-10;
                     size="S";
                     // בחר S
                 } else if (checkedId == R.id.rbMedium) {
                     sizePrice=basePrice;
                     size="M";
                     // בחר M
                 } else if (checkedId == R.id.rbLarge) {
                     sizePrice=basePrice+10;
                     size="L";
                     // בחר L
                 } else if (checkedId == R.id.rbXLarge) {
                     sizePrice=basePrice+20;
                     size="XL";
                     // בחר XL
                 }
                 totalPrice=sizePrice+extrasPrice;
                 tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);

             }
         });
         cbTomato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("tomato");

                     // נסמן — הוסף למחיר
                 } else {

                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("tomato");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);

                 }
             }
         });
         cbCorn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("corn");

                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("corn");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                 }
             }
         });
         cbOlives.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("olives");

                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("olives");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                 }
             }
         });
         cbMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("mushrooms");

                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("mushrooms");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                 }
             }
         });
         cbPepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("pepper");
                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("pepper");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                 }
             }
         });
         cbOnion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("onion");
                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("onion");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                 }
             }
         });
         cbPineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+6;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("pineapple");
                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-6;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("pineapple");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);                 }
             }
         });
         cbTuna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+8;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("tunaFish");
                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-8;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("tunaFish");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);                 }
             }
         });
         cbBlacklOlive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked) {
                     extrasPrice=extrasPrice+5;
                     totalPrice=extrasPrice+sizePrice;
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
                     extras.add("blackOlive");
                     // נסמן — הוסף למחיר
                 } else {
                     extrasPrice=extrasPrice-5;
                     totalPrice=sizePrice+extrasPrice;
                     extras.remove("blackOlive");
                     tvTotalPrice.setText("סך הכול לתשלום: ₪" + totalPrice);                 }
             }
         });
         btnAddToCart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(PizzaDetailsActivity.this, cart2.class);
                 intent.putExtra("totalPrice",totalPrice);
                 intent.putExtra("pizzaName",pizzaName);
                 intent.putExtra("pizzaImage",pizzaImage);
                 intent.putExtra("pizzaSize",size);
                 intent.putExtra("pizzaExtras",extras);
                 startActivity(intent);
             }
         });



     }
    }
