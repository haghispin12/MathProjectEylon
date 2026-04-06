package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private Button btnAddMozzarella;
    private Button btnAddThick;
    private Button btnAddThin;
    private Button btnAddPersonal;
    private Button btnAddFourCheese;
    private Button btnAddExtraCheese;
    private Button btnAddGreek;
    private Button btnAddGlutenFree;
    private Button btnAddSquare;
    private Pizza pizza;


    // כפתור תשלום
    private Button btnCheckout;

    private Intent intent;

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
        btnAddMozzarella = findViewById(R.id.btnAddMozzarella);
        btnAddThick = findViewById(R.id.btnAddThick);
        btnAddThin = findViewById(R.id.btnAddThin);
        btnAddPersonal = findViewById(R.id.btnAddPersonal);
        btnAddFourCheese = findViewById(R.id.btnAddFourCheese);
        btnAddExtraCheese = findViewById(R.id.btnAddExtraCheese);
        btnAddGreek = findViewById(R.id.btnAddGreek);
        btnAddGlutenFree = findViewById(R.id.btnAddGlutenFree);
        btnAddSquare = findViewById(R.id.btnAddSquare);
        init();

        };
    public void init(){
        cardMozzarella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","מוצרלה");
                intent.putExtra("pizzaImage","classicpizza");
                int x=0;
                intent.putExtra("pizzaPrice","45");
                startActivity(intent);

            }
        });
        cardThick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה עבה");
                intent.putExtra("pizzaImage","thickpizza");
                intent.putExtra("pizzaPrice","50");
                startActivity(intent);

            }
        });
        cardThin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה דקה");
                intent.putExtra("pizzaImage","thinpizza");
                intent.putExtra("pizzaPrice","50");
                startActivity(intent);
            }
        });
        cardPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה אישית");
                intent.putExtra("pizzaImage","personalpizza");
                intent.putExtra("pizzaPrice","25");
                startActivity(intent);

            }
        });
        cardFourCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","ארבע גבינות");
                intent.putExtra("pizzaImage","fourcheesepizza");
                intent.putExtra("pizzaPrice","60");
                startActivity(intent);

            }
        });
        cardExtraCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","אקסטרה גבינה");
                intent.putExtra("pizzaImage","extracheesepizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);

            }
        });
        cardGreek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה יוונית");
                intent.putExtra("pizzaImage","greekpizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);

            }
        });
        cardGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","ללא גלוטן");
                intent.putExtra("pizzaImage","glutenfreepizza");
                intent.putExtra("pizzaPrice","60");
                startActivity(intent);

            }
        });

        cardSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (MainActivityPizza.this, PizzaDetailsActivity.class);
                intent.putExtra("pizzaName","פיצה מרובעת");
                intent.putExtra("pizzaImage","squarepizza");
                intent.putExtra("pizzaPrice","55");
                startActivity(intent);
            }
        });
        btnAddMozzarella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buyer.currentBuyer = new Buyer("טסט", "טסט", "test@test.com", 1234, 1234, "כתובת", 050);
                Pizza mozzarella=new Pizza(45,"pizzaMozzarella","M",null,"classicpizza");
                Buyer.currentBuyer.addToCart(mozzarella);
                Toast.makeText(MainActivityPizza.this, "פיצה מוצרלה נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddThick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza thick=new Pizza(50,"pizzaThick","M",null,"thickpizza");
                Buyer.currentBuyer.addToCart(thick);
                Toast.makeText(MainActivityPizza.this, "פיצה עבה נוספה לעגלה", Toast.LENGTH_SHORT).show();
            }
        });

        btnAddThin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza thin=new Pizza(50,"pizzaThin","M",null,"thinpizza");
                Buyer.currentBuyer.addToCart(thin);
                Toast.makeText(MainActivityPizza.this, "פיצה דקה נוספה לעגלה", Toast.LENGTH_SHORT).show();

            }
        });

        btnAddPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza personal=new Pizza(25,"pizzaPersonal","M",null,"personapizza");
                Buyer.currentBuyer.addToCart(personal);
                Toast.makeText(MainActivityPizza.this, "פיצה אישית נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddFourCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza fourCheese=new Pizza(60,"pizzaFourCheese","M",null,"fourcheesepizza");
                Buyer.currentBuyer.addToCart(fourCheese);
                Toast.makeText(MainActivityPizza.this, "פיצה ארבע גבינות נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddExtraCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza extraCheese=new Pizza(55,"pizzaExtraCheese","M",null,"extracheese");
                Buyer.currentBuyer.addToCart(extraCheese);
                Toast.makeText(MainActivityPizza.this, "פיצה אקסטרה גבינה נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddGreek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza greek=new Pizza(55,"pizzaGreek","M",null,"greekpizza");
                Buyer.currentBuyer.addToCart(greek);
                Toast.makeText(MainActivityPizza.this, "פיצה יוונית נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddGlutenFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza glutenFree=new Pizza(60,"pizzaGlutenFree","M",null,"glutenfreepizza");
                Buyer.currentBuyer.addToCart(glutenFree);
                Toast.makeText(MainActivityPizza.this, "פיצה ללא גלוטן נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });

        btnAddSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizza square =new Pizza(55,"pizzaSquare","M",null,"squarepizza");
                Buyer.currentBuyer.addToCart(square);
                Toast.makeText(MainActivityPizza.this, "פיצה מרובעת נוספה לעגלה", Toast.LENGTH_SHORT).show();


            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivityPizza.this, cart2.class);
                int x=1;
                startActivity(intent);
                int y=0;
            }
        });
    }
    }
