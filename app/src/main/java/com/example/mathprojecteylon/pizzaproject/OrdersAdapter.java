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

/**
 * Adapter להצגת רשימת ההזמנות של הלקוח.
 * כל פריט ברשימה מציג: סטטוס, פיצות, מחיר וטיימר ספירה לאחור.
 * משמש את OrdersActivity עם RecyclerView.
 */
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    // רשימת ההזמנות — כל הזמנה היא Map של שדות מ-Firestore
    private ArrayList<Map<String, Object>> orders;

    /**
     * קונסטרקטור — מקבל את רשימת ההזמנות מ-OrdersActivity
     */
    public OrdersAdapter(ArrayList<Map<String, Object>> orders) {
        this.orders = orders;
    }

    /**
     * ViewHolder — מחזיק את כל התצוגות של פריט אחד ברשימה.
     * שימוש ב-ViewHolder חוסך זיכרון כי לא צריך לחפש את התצוגות בכל פעם
     */
    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrderStatus;  // סטטוס ההזמנה
        TextView tvOrderItems;   // שמות הפיצות
        TextView tvOrderTotal;   // סכום כולל
        TextView tvOrderTimer;   // טיימר ספירה לאחור

        public OrderViewHolder(View itemView) {
            super(itemView);
            tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);
            tvOrderItems = itemView.findViewById(R.id.tvOrderItems);
            tvOrderTotal = itemView.findViewById(R.id.tvOrderTotal);
            tvOrderTimer = itemView.findViewById(R.id.tvOrderTimer);
        }
    }

    /**
     * יוצר ViewHolder חדש — נקרא כשצריך פריט חדש ברשימה.
     * מנפח את ה-XML של הפריט (item_order.xml)
     */
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    /**
     * ממלא את הנתונים בפריט ברשימה — נקרא לכל פריט גלוי.
     * מציג את פרטי ההזמנה ומפעיל טיימר ספירה לאחור אם הוגדר זמן
     */
    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // שליפת ההזמנה הספציפית לפי מיקום ברשימה
        Map<String, Object> order = orders.get(position);

        // הצגת סטטוס, מחיר ופיצות
        holder.tvOrderStatus.setText("סטטוס: " + order.get("status"));
        holder.tvOrderTotal.setText("סכום: ₪" + order.get("totalPrice"));
        holder.tvOrderItems.setText("פיצות: " + order.get("cart").toString());

        // שליפת זמן ההכנה שהמנהל הגדיר
        Object timeObj = order.get("estimatedTime");
        int estimatedTime = timeObj != null ? ((Long) timeObj).intValue() : 0;

        if (estimatedTime == 0) {
            // המנהל עדיין לא הגדיר זמן
            holder.tvOrderTimer.setText("זמן משוער: ממתין");
        } else {
            // המנהל הגדיר זמן — מפעילים טיימר ספירה לאחור
            // ממירים דקות למילישניות (דקות * 60 שניות * 1000 מילישניות)
            long milliseconds = estimatedTime * 60 * 1000;

            new CountDownTimer(milliseconds, 1000) {
                // נקרא כל שנייה — מעדכן את התצוגה
                @Override
                public void onTick(long millisUntilFinished) {
                    int minutesLeft = (int) (millisUntilFinished / 1000 / 60);
                    int secondsLeft = (int) (millisUntilFinished / 1000 % 60);
                    // String.format("%02d") מוודא שהשניות תמיד מוצגות בשתי ספרות
                    holder.tvOrderTimer.setText("זמן שנותר: " + minutesLeft + ":" + String.format("%02d", secondsLeft));
                }

                // נקרא כשהטיימר הגיע לאפס
                @Override
                public void onFinish() {
                    holder.tvOrderTimer.setText("ההזמנה מוכנה!");
                }
            }.start();
        }
    }

    /**
     * מחזיר את מספר הפריטים ברשימה — נדרש על ידי RecyclerView
     */
    @Override
    public int getItemCount() {
        return orders.size();
    }
}