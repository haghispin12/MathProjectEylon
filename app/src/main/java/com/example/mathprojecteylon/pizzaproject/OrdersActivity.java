package com.example.mathprojecteylon.pizzaproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerOrders;
    private FirebaseFirestore db;
    private ArrayList<Map<String, Object>> ordersList;
    private OrdersAdapter adapter;
    private HashMap<Integer, CountDownTimer> timers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }

        recyclerOrders = findViewById(R.id.recyclerOrders);
        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();
        adapter = new OrdersAdapter(ordersList);
        recyclerOrders.setAdapter(adapter);
        loadOrders();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (CountDownTimer timer : timers.values()) {
            timer.cancel();
        }
    }

    public void sendNotification() {
        String channelId = "pizza_channel";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId, "Pizza Orders", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(OrdersActivity.this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("הפיצה מוכנה!")
                .setContentText("ההזמנה שלך מוכנה לאיסוף")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);
        notificationManager.notify(1, builder.build());
    }

    public void loadOrders() {
        db.collection("orders")
                .whereEqualTo("email", Buyer.currentBuyer.getEmailS())
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) return;
                    for (CountDownTimer timer : timers.values()) {
                        timer.cancel();
                    }
                    timers.clear();
                    ordersList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Map<String, Object> data = doc.getData();
                        data.put("docId", doc.getId());
                        ordersList.add(data);
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

            ArrayList<Map<String, Object>> cart = (ArrayList<Map<String, Object>>) order.get("cart");
            StringBuilder pizzaNames = new StringBuilder("פיצות: ");
            for (int i = 0; i < cart.size(); i++) {
                pizzaNames.append(cart.get(i).get("name"));
                if (i < cart.size() - 1) pizzaNames.append(", ");
            }
            holder.tvOrderItems.setText(pizzaNames.toString());

            Object timeObj = order.get("estimatedTime");
            Object startObj = order.get("startTime");
            int estimatedTime = timeObj != null ? ((Long) timeObj).intValue() : 0;

            if (timers.containsKey(position)) {
                timers.get(position).cancel();
                timers.remove(position);
            }

            if (estimatedTime == 0 || startObj == null) {
                holder.tvOrderTimer.setText("זמן משוער: ממתין");
            } else {
                long startTime = (Long) startObj;
                long elapsed = System.currentTimeMillis() - startTime;
                long remaining = (long) estimatedTime * 60 * 1000 - elapsed;
                if (remaining <= 0) {
                    holder.tvOrderTimer.setText("ההזמנה מוכנה!");
                } else {
                    CountDownTimer timer = new CountDownTimer(remaining, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            int minutesLeft = (int) (millisUntilFinished / 1000 / 60);
                            int secondsLeft = (int) (millisUntilFinished / 1000 % 60);
                            holder.tvOrderTimer.setText("זמן שנותר: " + minutesLeft + ":" + String.format("%02d", secondsLeft));
                        }
                        @Override
                        public void onFinish() {
                            holder.tvOrderTimer.setText("ההזמנה מוכנה!");
                            String docId = (String) order.get("docId");
                            db.collection("orders").document(docId)
                                    .update("status", "מוכן");
                            sendNotification();
                        }
                    }.start();
                    timers.put(position, timer);
                }
            }
        }

        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}