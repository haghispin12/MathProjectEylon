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

/**
 * מסך פרטי הזמנות למנהל.
 * מציג רק הזמנות שעדיין לא טופלו — סטטוס "מחכה לאישור המנהל" או "בהכנה".
 * כשהזמנה עוברת לסטטוס "מוכן" — היא יורדת אוטומטית מהרשימה.
 *
 * RecyclerView הוא רשימה גמישה וחסכונית בזיכרון.
 * במקום ליצור תצוגה לכל פריט ברשימה — הוא מחזיר ומשתמש שוב בתצוגות שכבר נוצרו.
 * לדוגמה: אם יש 100 הזמנות אבל המסך מציג 5 — הוא יצור רק 5-6 תצוגות ויחזיר בהן שימוש.
 */
public class OrderDetailsActivity extends AppCompatActivity {

    // RecyclerView — הרכיב שמציג את הרשימה על המסך
    private RecyclerView recyclerOrderDetails;

    // אובייקט גישה ל-Firestore
    private FirebaseFirestore db;

    // הרשימה שמחזיקה את ההזמנות בזיכרון
    private ArrayList<Map<String, Object>> ordersList;

    // ה-Adapter — הגשר בין הנתונים לבין התצוגה (מוסבר למטה)
    private OrderDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        recyclerOrderDetails = findViewById(R.id.recyclerOrderDetails);

        // LinearLayoutManager קובע שהפריטים יוצגו אחד מתחת לשני (אנכית)
        // אפשר גם GridLayoutManager לרשת או HorizontalLayoutManager לאופקי
        recyclerOrderDetails.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        ordersList = new ArrayList<>();

        // יצירת ה-Adapter ומסירתו ל-RecyclerView
        adapter = new OrderDetailsAdapter(ordersList);
        recyclerOrderDetails.setAdapter(adapter);

        loadOrders();
    }

    /**
     * שולף מ-Firestore רק הזמנות עם סטטוס "מחכה לאישור המנהל" או "בהכנה".
     * משתמש ב-addSnapshotListener — מאזין לשינויים בזמן אמת.
     * כשהזמנה עוברת ל"מוכן" — היא יורדת אוטומטית מהרשימה.
     * whereIn מאפשר לסנן לפי מספר ערכים בבת אחת
     */
    public void loadOrders() {
        db.collection("orders")
                .whereIn("status", Arrays.asList("מחכה לאישור המנהל", "בהכנה"))
                .addSnapshotListener((queryDocumentSnapshots, error) -> {
                    if (error != null) return;
                    ordersList.clear();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        ordersList.add(doc.getData());
                    }
                    // עדכון ה-RecyclerView שהנתונים השתנו
                    adapter.notifyDataSetChanged();
                });
    }

    // ===================================================================
    // ה-ADAPTER — הלב של ה-RecyclerView
    // ===================================================================
    /**
     * OrderDetailsAdapter — הגשר בין רשימת ההזמנות לבין המסך.
     *
     * RecyclerView לא יודע איך להציג הזמנה — הוא שואל את ה-Adapter:
     * 1. כמה פריטים יש? (getItemCount)
     * 2. איך נראה פריט אחד? (onCreateViewHolder)
     * 3. מה הנתונים של פריט מסוים? (onBindViewHolder)
     *
     * הוגדר כמחלקה פנימית כדי שתהיה לה גישה ישירה לנתונים של OrderDetailsActivity
     */
    public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.OrderDetailsViewHolder> {

        // הרשימה שה-Adapter מציג
        private ArrayList<Map<String, Object>> orders;

        public OrderDetailsAdapter(ArrayList<Map<String, Object>> orders) {
            this.orders = orders;
        }

        // ===================================================================
        // ה-VIEWHOLDER — מחזיק את התצוגות של פריט אחד
        // ===================================================================
        /**
         * OrderDetailsViewHolder — מחזיק הפניות לכל התצוגות של פריט אחד.
         *
         * בלי ViewHolder — ה-RecyclerView היה צריך לחפש את כל תצוגה בכל פעם (יקר מבחינת ביצועים).
         * עם ViewHolder — הפניות נשמרות פעם אחת ונגישות מיד.
         */
        public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {
            TextView tvDetailEmail;      // אימייל הלקוח
            TextView tvDetailStatus;     // סטטוס ההזמנה
            TextView tvDetailTotal;      // סכום כולל
            TextView tvDetailPizzasList; // פרטי הפיצות עם גדלים ותוספות

            public OrderDetailsViewHolder(View itemView) {
                super(itemView);
                // חיבור כל משתנה לתצוגה המתאימה ב-XML של הפריט (item_order_details.xml)
                tvDetailEmail = itemView.findViewById(R.id.tvDetailEmail);
                tvDetailStatus = itemView.findViewById(R.id.tvDetailStatus);
                tvDetailTotal = itemView.findViewById(R.id.tvDetailTotal);
                tvDetailPizzasList = itemView.findViewById(R.id.tvDetailPizzasList);
            }
        }

        /**
         * onCreateViewHolder — נקרא כשה-RecyclerView צריך פריט חדש.
         * מנפח את ה-XML של הפריט (item_order_details.xml) ויוצר ViewHolder.
         * נקרא מספר מוגבל של פעמים — רק כשצריך פריט שעדיין לא נוצר.
         */
        @NonNull
        @Override
        public OrderDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // LayoutInflater הופך את ה-XML לתצוגה אמיתית על המסך
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_order_details, parent, false);
            return new OrderDetailsViewHolder(view);
        }

        /**
         * onBindViewHolder — נקרא כשצריך למלא פריט קיים בנתונים חדשים.
         * זה המקום שבו מחברים בין הנתונים לבין התצוגה.
         * נקרא בכל פעם שפריט נכנס לתחום הנראות של המסך.
         *
         * @param position — מיקום הפריט ברשימה (0, 1, 2...)
         */
        @Override
        public void onBindViewHolder(@NonNull OrderDetailsViewHolder holder, int position) {
            // שליפת ההזמנה הספציפית לפי המיקום ברשימה
            Map<String, Object> order = orders.get(position);

            // מילוי הנתונים הבסיסיים
            holder.tvDetailEmail.setText("אימייל: " + order.get("email"));
            holder.tvDetailStatus.setText("סטטוס: " + order.get("status"));
            holder.tvDetailTotal.setText("סכום: ₪" + order.get("totalPrice"));

            // בניית מחרוזת מפורטת של כל הפיצות בהזמנה
            // כל פיצה מוצגת עם שם, גודל ותוספות
            ArrayList<Map<String, Object>> cart = (ArrayList<Map<String, Object>>) order.get("cart");
            StringBuilder pizzaDetails = new StringBuilder();
            for (int i = 0; i < cart.size(); i++) {
                Map<String, Object> pizza = cart.get(i);
                pizzaDetails.append("• ").append(pizza.get("name"));
                pizzaDetails.append(" | גודל: ").append(pizza.get("size"));

                // בדיקה אם יש תוספות — אם יש מציגים, אם אין מציגים "אין"
                Object extras = pizza.get("extras");
                if (extras != null && !extras.toString().equals("[]")) {
                    // הסרת הסוגריים מהרשימה לתצוגה נקייה
                    pizzaDetails.append(" | תוספות: ").append(extras.toString()
                            .replace("[", "").replace("]", ""));
                } else {
                    pizzaDetails.append(" | תוספות: אין");
                }
                pizzaDetails.append("\n");
            }
            holder.tvDetailPizzasList.setText(pizzaDetails.toString());
        }

        /**
         * getItemCount — מחזיר את מספר הפריטים ברשימה.
         * ה-RecyclerView קורא לפונקציה זו כדי לדעת כמה פריטים להציג.
         */
        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}