package com.example.mathprojecteylon.pizzaproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojecteylon.R;

import java.util.ArrayList;
import java.util.Map;
import android.os.CountDownTimer;

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
            long milliseconds = estimatedTime * 60 * 1000;
            new CountDownTimer(milliseconds, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int minutesLeft = (int) (millisUntilFinished / 1000 / 60);
                    int secondsLeft = (int) (millisUntilFinished / 1000 % 60);
                    holder.tvOrderTimer.setText("זמן שנותר: " + minutesLeft + ":" + String.format("%02d", secondsLeft));
                }
                @Override
                public void onFinish() {
                    holder.tvOrderTimer.setText("ההזמנה מוכנה!");
                }
            }.start();
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
