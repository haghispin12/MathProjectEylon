package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Map;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerOrders;
    private FirebaseFirestore db;
    private ArrayList<Map<String, Object>> ordersList;
    private OrdersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        recyclerOrders = findViewById(R.id.recyclerOrders);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();
        adapter = new OrdersAdapter(ordersList);
        recyclerOrders.setAdapter(adapter);
        loadOrders();
    }

    public void loadOrders() {
        db.collection("orders")
                .whereEqualTo("email", Buyer.currentBuyer.getEmailS())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    ordersList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ordersList.add((Map<String, Object>) doc.getData());
                    }
                    adapter.notifyDataSetChanged();
                });
    }
}