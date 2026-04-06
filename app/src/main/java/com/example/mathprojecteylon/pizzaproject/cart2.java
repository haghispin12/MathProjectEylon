package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojecteylon.R;

public class cart2 extends AppCompatActivity {
    private Button pay;
    private Button back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        cartAdapter adapter = new cartAdapter(Buyer.currentBuyer.getCart());
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);
        pay=findViewById(R.id.btnCheckout);
        back=findViewById(R.id.btnBack);
        init();

    }
    public void init(){
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.orders.add(Buyer.currentBuyer);
                Toast.makeText(cart2.this, "ההזמנה התקבלה!", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(cart2.this, MainActivityPizza.class);
                startActivity(intent);
            }
        });
    }
}