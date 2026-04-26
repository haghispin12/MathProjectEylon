package com.example.mathprojecteylon.pizzaproject;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView recyclerAdminOrders;
    private FirebaseFirestore db;
    private ArrayList<Map<String, Object>> ordersList;
    private ArrayList<String> orderIds;
    private AdminAdapter adapter;

    // מפה של טיימרים — כדי לבטל אותם כשהמסך נסגר
    private HashMap<Integer, CountDownTimer> timers = new HashMap<>();

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

    // ביטול כל הטיימרים כשהמסך נסגר
    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (CountDownTimer timer : timers.values()) {
            timer.cancel();
        }
    }

    public void loadOrders() {
        db.collection("orders")
                .whereIn("status", Arrays.asList("מחכה לאישור המנהל", "בהכנה"))
                .orderBy(FieldPath.documentId())
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) return;

                    ArrayList<Map<String, Object>> newOrders = new ArrayList<>();
                    ArrayList<String> newIds = new ArrayList<>();

                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        newOrders.add(doc.getData());
                        newIds.add(doc.getId());
                    }

                    for (int i = 0; i < newOrders.size(); i++) {
                        if (i < ordersList.size()) {
                            ordersList.set(i, newOrders.get(i));
                            orderIds.set(i, newIds.get(i));
                            adapter.notifyItemChanged(i);
                        } else {
                            ordersList.add(newOrders.get(i));
                            orderIds.add(newIds.get(i));
                            adapter.notifyItemInserted(i);
                        }
                    }

                    while (ordersList.size() > newOrders.size()) {
                        ordersList.remove(ordersList.size() - 1);
                        orderIds.remove(orderIds.size() - 1);
                        adapter.notifyItemRemoved(ordersList.size());
                    }
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
            TextView tvAdminEmail;
            TextView tvAdminItems;
            TextView tvAdminTotal;
            TextView tvAdminStatus;
            TextView tvAdminTimer;
            Button btnWaiting;
            Button btnPreparing;
            Button btnReady;
            Button btnReject;
            Button btnSetTimer;
            EditText etTimer;

            public AdminViewHolder(View itemView) {
                super(itemView);
                tvAdminEmail = itemView.findViewById(R.id.tvAdminEmail);
                tvAdminItems = itemView.findViewById(R.id.tvAdminItems);
                tvAdminTotal = itemView.findViewById(R.id.tvAdminTotal);
                tvAdminStatus = itemView.findViewById(R.id.tvAdminStatus);
                tvAdminTimer = itemView.findViewById(R.id.tvAdminTimer);
                btnWaiting = itemView.findViewById(R.id.btnWaiting);
                btnPreparing = itemView.findViewById(R.id.btnPreparing);
                btnReady = itemView.findViewById(R.id.btnReady);
                btnReject = itemView.findViewById(R.id.btnReject);
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

            // ===== טיימר =====
            Object timeObj = order.get("estimatedTime");
            Object startObj = order.get("startTime");
            int estimatedTime = timeObj != null ? ((Long) timeObj).intValue() : 0;

            // ביטול טיימר קודם לפריט הזה אם קיים
            if (timers.containsKey(position)) {
                timers.get(position).cancel();
                timers.remove(position);
            }

            if (estimatedTime == 0 || startObj == null) {
                holder.tvAdminTimer.setText("⏱ זמן שנותר: ממתין");
            } else {
                long startTime = (Long) startObj;
                long elapsed = System.currentTimeMillis() - startTime;
                long remaining = (long) estimatedTime * 60 * 1000 - elapsed;

                if (remaining <= 0) {
                    holder.tvAdminTimer.setText("⏱ הזמנה מוכנה!");
                } else {
                    CountDownTimer timer = new CountDownTimer(remaining, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int minutesLeft = (int) (millisUntilFinished / 1000 / 60);
                            int secondsLeft = (int) (millisUntilFinished / 1000 % 60);
                            holder.tvAdminTimer.setText("⏱ זמן שנותר: " + minutesLeft + ":" + String.format("%02d", secondsLeft));
                        }

                        @Override
                        public void onFinish() {
                            holder.tvAdminTimer.setText("⏱ הזמנה מוכנה!");
                        }
                    }.start();
                    timers.put(position, timer);
                }
            }

            holder.btnWaiting.setOnClickListener(v -> updateStatus(orderId, "מחכה לאישור המנהל"));
            holder.btnPreparing.setOnClickListener(v -> updateStatus(orderId, "בהכנה"));
            holder.btnReady.setOnClickListener(v -> updateStatus(orderId, "מוכן"));
            holder.btnReject.setOnClickListener(v -> updateStatus(orderId, "ההזמנה לא התקבלה"));

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

        public void updateStatus(String orderId, String status) {
            db.collection("orders").document(orderId)
                    .update("status", status)
                    .addOnSuccessListener(unused ->
                            Toast.makeText(AdminActivity.this, "סטטוס עודכן!", Toast.LENGTH_SHORT).show());
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}