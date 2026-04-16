package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import android.util.Log;
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
        Log.d("Orders", "מתחיל לטעון הזמנות");
        Log.d("Orders", "אימייל: " + Buyer.currentBuyer.getEmailS());
        db.collection("orders")
                .whereEqualTo("email", Buyer.currentBuyer.getEmailS())
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("Orders", "מספר הזמנות: " + queryDocumentSnapshots.size());
                    ordersList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ordersList.add((Map<String, Object>) doc.getData());
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

        private ArrayList<Map<String, Object>> orders;

        public OrdersAdapter(ArrayList<Map<String, Object>> orders) {
            this.orders = orders;
        }

        public class OrderViewHolder extends RecyclerView.ViewHolder {
            TextView tvOrderStatus;
            TextView tvOrderItems;
            TextView tvOrderTotal;
            TextView tvOrderTimer;

            public OrderViewHolder(View itemView) {
                super(itemView);
                tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
                tvOrderItems = itemView.findViewById(R.id.tvOrderItems);
                tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
                tvOrderTimer = itemView.findViewById(R.id.tvOrderTimer);
            }
        }

        @NonNull
        @Override
        public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order, parent, false);
            return new OrderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
            Map<String, Object> order = orders.get(position);
            holder.tvOrderStatus.setText("סטטוס: " + order.get("status"));
            holder.tvOrderTotal.setText("סכום: ₪" + order.get("totalPrice"));
            holder.tvOrderItems.setText("פיצות: " + order.get("cart").toString());
            Object timeObj = order.get("estimatedTime");
            int estimatedTime = timeObj != null ? ((Long) timeObj).intValue() : 0;
            if (estimatedTime == 0) {
                holder.tvOrderTimer.setText("זמן משוער: ממתין");
            } else {
                holder.tvOrderTimer.setText("זמן משוער: " + estimatedTime + " דקות");
            }
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}