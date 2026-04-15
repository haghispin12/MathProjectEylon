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
    private FirebaseAuth Mauth;

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
        Mauth=FirebaseAuth.getInstance();
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
                Toast.makeText(cart2.this, "נלחץ!", Toast.LENGTH_SHORT).show();
                Map<String, Object> invent = new HashMap<>();
                invent.put("name", "Los Angeles");
                invent.put("state", "CA");
                invent.put("country", "USA");

               db.collection("invents").add(invent).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                   @Override
                   public void onComplete(@NonNull Task<DocumentReference> task) {
                       int n =0;
                   }
               });


//                db.collection("invents")
//                        .add(invent)
//
//                        .addOnSuccessListener(documentReference -> {
//                            Toast.makeText(cart2.this, "הועלה בהצלחה", Toast.LENGTH_SHORT).show();
//                        })
//                        .addOnFailureListener(e -> {
//                            Toast.makeText(cart2.this, "שגיאה", Toast.LENGTH_SHORT).show();
//                        });
            }
        });
    }
}

//                        )
//                @Override
//                            public void onSuccess(Void aVoid) {
//                                Log.d("haggay", "DocumentSnapshot successfully written!");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("haggay", "Error writing document", e);
//                            }
//                        });
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String email = user.getEmail();
//               Toast.makeText(cart2.this, "נלחץ!", Toast.LENGTH_SHORT).show();
//                db.collection("Buyers").document("test")
//                        .set(Buyer.currentBuyer)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                           @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(cart2.this, "ההזמנה נשמרה!", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(cart2.this, "ההזמנה איננה נשמרה", Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }
//        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(cart2.this, MainActivityPizza.class);
//                startActivity(intent);
//            }
//        });
//    }
//}



