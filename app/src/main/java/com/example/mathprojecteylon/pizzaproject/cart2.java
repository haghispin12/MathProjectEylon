package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojecteylon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class cart2 extends AppCompatActivity {
    private Button pay;
    private Button back;
    private FirebaseFirestore db;
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        RecyclerView recyclerCart = findViewById(R.id.recyclerCart);
        cartAdapter adapter = new cartAdapter(Buyer.currentBuyer.getCart(), new cartAdapter.OnCartChangedListener() {
            @Override
            public void onCartChanged() {
                updateTotal();
            }
        });
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);
        pay = findViewById(R.id.btnCheckout);
        back = findViewById(R.id.btnBack);
        totalPrice = findViewById(R.id.tvTotalPrice);
        db = FirebaseFirestore.getInstance();
        init();
        updateTotal();
    }

    public void updateTotal() {
        int total = 0;
        for (Pizza pizza : Buyer.currentBuyer.getCart()) {
            total += pizza.getPrice();
        }
        totalPrice.setText("₪" + total);
    }

    public void init() {
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                for (int i = 0; i < Buyer.currentBuyer.getCart().size(); i++) {
                    total += Buyer.currentBuyer.getCart().get(i).getPrice();
                }

                Map<String, Object> orderData = new HashMap<>();
                orderData.put("email", Buyer.currentBuyer.getEmailS());
                orderData.put("cart", Buyer.currentBuyer.getCart());
                orderData.put("totalPrice", total);
                orderData.put("status", "ממתין");
                orderData.put("estimatedTime", 0);

                db.collection("orders").document()
                        .set(orderData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(cart2.this, "ההזמנה נשמרה!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(cart2.this, OrdersActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(cart2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart2.this, MainActivityPizza.class);
                startActivity(intent);
            }
        });
    }
}








