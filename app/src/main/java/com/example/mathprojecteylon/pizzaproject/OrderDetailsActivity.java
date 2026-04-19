package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class OrderDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerOrderDetails;
    private FirebaseFirestore db;
    private ArrayList<Map<String, Object>> ordersList;
    private OrderDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        recyclerOrderDetails = findViewById(R.id.recyclerOrderDetails);
        recyclerOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();
        adapter = new OrderDetailsAdapter(ordersList);
        recyclerOrderDetails.setAdapter(adapter);
        loadOrders();
    }

    public void loadOrders() {
        db.collection("orders")
                .whereIn("status", Arrays.asList("מחכה לאישור המנהל", "בהכנה"))
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) return;
                    ordersList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ordersList.add(doc.getData());
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

        private ArrayList<Map<String, Object>> orders;

        public OrderDetailsAdapter(ArrayList<Map<String, Object>> orders) {
            this.orders = orders;
        }

        public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
            TextView tvDetailEmail;
            TextView tvDetailStatus;
            TextView tvDetailTotal;
            TextView tvDetailPizzasList;

            public OrderDetailsViewHolder(View itemView) {
                super(itemView);
                tvDetailEmail = itemView.findViewById(R.id.tvDetailEmail);
                tvDetailStatus = itemView.findViewById(R.id.tvDetailStatus);
                tvDetailTotal = itemView.findViewById(R.id.tvDetailTotal);
                tvDetailPizzasList = itemView.findViewById(R.id.tvDetailPizzasList);
            }
        }

        @NonNull
        @Override
        public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order_details, parent, false);
            return new OrderDetailsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
            Map<String, Object> order = orders.get(position);

            holder.tvDetailEmail.setText("אימייל: " + order.get("email"));
            holder.tvDetailStatus.setText("סטטוס: " + order.get("status"));
            holder.tvDetailTotal.setText("סכום: ₪" + order.get("totalPrice"));

            ArrayList<Map<String, Object>> cart = (ArrayList<Map<String, Object>>) order.get("cart");
            StringBuilder pizzaDetails = new StringBuilder();
            for (int i = 0; i < cart.size(); i++) {
                Map<String, Object> pizza = cart.get(i);
                pizzaDetails.append("• ").append(pizza.get("name"));
                pizzaDetails.append(" | גודל: ").append(pizza.get("size"));
                Object extras = pizza.get("extras");
                if (extras != null && !extras.toString().equals("[]")) {
                    pizzaDetails.append(" | תוספות: ").append(extras.toString()
                            .replace("[", "").replace("]", ""));
                } else {
                    pizzaDetails.append(" | תוספות: אין");
                }
                pizzaDetails.append("\n");
            }
            holder.tvDetailPizzasList.setText(pizzaDetails.toString());
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}