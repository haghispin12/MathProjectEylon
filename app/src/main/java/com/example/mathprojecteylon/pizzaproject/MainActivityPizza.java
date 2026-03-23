package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mathprojecteylon.R;

public class MainActivityPizza extends AppCompatActivity {
    // כרטיסי פיצות
    private LinearLayout cardMozzarella;
    private LinearLayout cardThick;
    private LinearLayout cardThin;
    private LinearLayout cardPersonal;
    private LinearLayout cardFourCheese;
    private LinearLayout cardExtraCheese;
    private LinearLayout cardGreek;
    private LinearLayout cardGlutenFree;
    private LinearLayout cardSquare;


    // כפתור תשלום
    private Button btnCheckout;

    // כפתור פרופיל
    private ImageView btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pizza);
        // חיבור המשתנים ל-XML
        cardMozzarella = findViewById(R.id.cardMozzarella);
        cardThick = findViewById(R.id.cardThick);
        cardThin = findViewById(R.id.cardThin);
        cardPersonal = findViewById(R.id.cardPersonal);
        cardFourCheese = findViewById(R.id.cardFourCheese);
        cardExtraCheese = findViewById(R.id.cardExtraCheese);
        cardGreek = findViewById(R.id.cardGreek);
        cardGlutenFree = findViewById(R.id.cardGlutenFree);
        cardSquare = findViewById(R.id.cardSquare);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnProfile = findViewById(R.id.btnProfile);
        init();

        };
    public void init(){
        cardMozzarella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","מוצרלה");
                intent.putExtra("pizzaImage","classicPizza");
                intent.putExtra("pizzaPrice","45");
                startActivity(intent);


            }
        });
        cardThick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה עבה");
                intent.putExtra("pizzaImage","thickPizza");
                intent.putExtra("pizzaPrice","50");
                startActivity(intent);

            }
        });
        cardThin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה דקה");
                intent.putExtra("pizzaImage","thinPizza");
                intent.putExtra("pizzaPrice","50");
                startActivity(intent);
            }
        });
        cardPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה אישית");
                intent.putExtra("pizzaImage","personalPizza");
                intent.putExtra("pizzaPrice","25");
                startActivity(intent);

            }
        });
        cardFourCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","ארבע גבינות");
                intent.putExtra("pizzaImage","fourCheesePizza");
                intent.putExtra("pizzaPrice","60");
                startActivity(intent);

            }
        });
        cardExtraCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","אקסטרה גבינה");
                intent.putExtra("pizzaImage","extraCheesePizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);

            }
        });
        cardGreek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה יוונית");
                intent.putExtra("pizzaImage","greekPizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);

            }
        });
        cardGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","ללא גלוטן");
                intent.putExtra("pizzaImage","glutenFreePizza");
                intent.putExtra("pizzaPrice","60");
                startActivity(intent);

            }
        });

        cardSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה מרובעת");
                intent.putExtra("pizzaImage","squarePizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);
            }
        });
    }
    }
