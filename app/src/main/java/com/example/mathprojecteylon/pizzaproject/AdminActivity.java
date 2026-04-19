package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView recyclerAdminOrders;
    private FirebaseFirestore db;
    private ArrayList<Map<String, Object>> ordersList;
    private ArrayList<String> orderIds;
    private AdminAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        recyclerAdminOrders = findViewById(R.id.recyclerAdminOrders);
        recyclerAdminOrders.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();
        orderIds = new ArrayList<>();
        adapter = new AdminAdapter(ordersList, orderIds);
        recyclerAdminOrders.setAdapter(adapter);
        loadOrders();
    }

    public void loadOrders() {
        db.collection("orders")
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) return;
                    ordersList.clear();
                    orderIds.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ordersList.add(doc.getData());
                        orderIds.add(doc.getId());
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder> {

        private ArrayList<Map<String, Object>> orders;
        private ArrayList<String> ids;

        public AdminAdapter(ArrayList<Map<String, Object>> orders, ArrayList<String> ids) {
            this.orders = orders;
            this.ids = ids;
        }

        public class AdminViewHolder extends RecyclerView.ViewHolder {
            TextView tvAdminEmail, tvAdminItems, tvAdminTotal, tvAdminStatus;
            Button btnWaiting, btnPreparing, btnReady, btnSetTimer;
            EditText etTimer;

            public AdminViewHolder(View itemView) {
                super(itemView);
                tvAdminEmail = itemView.findViewById(R.id.tvAdminEmail);
                tvAdminItems = itemView.findViewById(R.id.tvAdminItems);
                tvAdminTotal = itemView.findViewById(R.id.tvAdminTotal);
                tvAdminStatus = itemView.findViewById(R.id.tvAdminStatus);
                btnWaiting = itemView.findViewById(R.id.btnWaiting);
                btnPreparing = itemView.findViewById(R.id.btnPreparing);
                btnReady = itemView.findViewById(R.id.btnReady);
                btnSetTimer = itemView.findViewById(R.id.btnSetTimer);
                etTimer = itemView.findViewById(R.id.etTimer);
            }
        }

        @NonNull
        @Override
        public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_admin, parent, false);
            return new AdminViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
            Map<String, Object> order = orders.get(position);
            String orderId = ids.get(position);

            holder.tvAdminEmail.setText("אימייל: " + order.get("email"));
            holder.tvAdminTotal.setText("סכום: ₪" + order.get("totalPrice"));
            holder.tvAdminStatus.setText("סטטוס: " + order.get("status"));

            ArrayList<Map<String, Object>> cart = (ArrayList<Map<String, Object>>) order.get("cart");
            StringBuilder pizzaNames = new StringBuilder("פיצות: ");
            for (int i = 0; i < cart.size(); i++) {
                pizzaNames.append(cart.get(i).get("name"));
                if (i < cart.size() - 1) pizzaNames.append(", ");
            }
            holder.tvAdminItems.setText(pizzaNames.toString());

            holder.btnWaiting.setOnClickListener(v -> updateStatus(orderId, "מחכה לאישור המנהל", position));
            holder.btnPreparing.setOnClickListener(v -> updateStatus(orderId, "בהכנה", position));
            holder.btnReady.setOnClickListener(v -> updateStatus(orderId, "מוכן", position));

            holder.btnSetTimer.setOnClickListener(v -> {
                String timeStr = holder.etTimer.getText().toString();
                if (!timeStr.isEmpty()) {
                    int time = Integer.parseInt(timeStr);
                    db.collection("orders").document(orderId)
                            .update("estimatedTime", time, "startTime", System.currentTimeMillis())
                            .addOnSuccessListener(unused ->
                                    Toast.makeText(AdminActivity.this, "זמן עודכן!", Toast.LENGTH_SHORT).show());
                }
            });
        }

        public void updateStatus(String orderId, String status, int position) {
            db.collection("orders").document(orderId)
                    .update("status", status)
                    .addOnSuccessListener(unused -> {
                        orders.get(position).put("status", status);
                        notifyItemChanged(position);
                        Toast.makeText(AdminActivity.this, "סטטוס עודכן!", Toast.LENGTH_SHORT).show();
                    });
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}