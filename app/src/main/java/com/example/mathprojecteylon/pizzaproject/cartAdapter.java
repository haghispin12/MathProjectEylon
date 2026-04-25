package com.example.mathprojecteylon.pizzaproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mathprojecteylon.R;
import java.util.ArrayList;

/**
 * Adapter לרשימת העגלה — מציג את הפיצות שהלקוח בחר.
 *
 * איך עובד RecyclerView עם Adapter:
 * RecyclerView הוא הרשימה על המסך — אבל הוא לא יודע איך להציג פיצה.
 * ה-Adapter הוא הגשר בין רשימת הפיצות לבין המסך.
 * הוא עונה על 3 שאלות שה-RecyclerView שואל:
 * 1. כמה פריטים יש? → getItemCount()
 * 2. איך נראה פריט? → onCreateViewHolder()
 * 3. מה הנתונים של פריט מסוים? → onBindViewHolder()
 */
public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {

    // רשימת הפיצות שנמצאות בעגלה
    private ArrayList<Pizza> cart;

    // ממשק (interface) שמאפשר לעגלה לדווח ל-cart2 כשפריט נמחק
    // כך cart2 יכול לעדכן את הסכום הכולל
    private OnCartChangedListener listener;

    /**
     * ממשק לעדכון cart2 כשהעגלה משתנה.
     * cart2 מממש את הממשק הזה ומקבל התראה כשמוחקים פיצה.
     */
    public interface OnCartChangedListener {
        void onCartChanged();
    }

    /**
     * קונסטרקטור — מקבל את רשימת הפיצות ואת המאזין לשינויים
     */
    public cartAdapter(ArrayList<Pizza> cart, OnCartChangedListener listener) {
        this.cart = cart;
        this.listener = listener;
    }

    // ===================================================================
    // ה-VIEWHOLDER — מחזיק את התצוגות של פריט אחד בעגלה
    // ===================================================================
    /**
     * CartViewHolder — שומר הפניות לכל התצוגות של פיצה אחת ברשימה.
     *
     * למה צריך ViewHolder?
     * כשגוללים את הרשימה, אנדרואיד מחזיר שימוש בתצוגות ישנות במקום ליצור חדשות.
     * ViewHolder שומר את הפניות לתצוגות כדי שלא צריך לחפש אותן שוב ושוב — חוסך זיכרון ומהיר יותר.
     */
    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPizzaImage;   // תמונת הפיצה
        TextView tvPizzaName;     // שם הפיצה
        TextView tvPizzaPrice;    // מחיר
        TextView tvPizzaSize;     // גודל
        TextView tvPizzaExtras;   // תוספות
        Button btnDelete;         // כפתור מחיקה מהעגלה

        public CartViewHolder(View itemView) {
            super(itemView);
            // חיבור כל משתנה לתצוגה המתאימה ב-XML של הפריט (activity_item2.xml)
            ivPizzaImage = itemView.findViewById(R.id.ivPizzaImage);
            tvPizzaName = itemView.findViewById(R.id.tvPizzaName);
            tvPizzaPrice = itemView.findViewById(R.id.tvPizzaPrice);
            tvPizzaSize = itemView.findViewById(R.id.tvPizzaSize);
            tvPizzaExtras = itemView.findViewById(R.id.tvPizzaExtras);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    /**
     * onCreateViewHolder — נקרא כשה-RecyclerView צריך פריט חדש.
     * מנפח את ה-XML של הפריט (activity_item2.xml) ויוצר ViewHolder.
     * נקרא מספר מוגבל של פעמים — רק כשצריך פריט שעדיין לא נוצר.
     */
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater הופך את קובץ ה-XML לתצוגה אמיתית על המסך
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item2, parent, false);
        return new CartViewHolder(view);
    }

    /**
     * onBindViewHolder — נקרא כשצריך למלא פריט קיים בנתונים.
     * זה המקום שבו מחברים בין נתוני הפיצה לבין התצוגה.
     * נקרא בכל פעם שפריט נכנס לתחום הנראות של המסך.
     *
     * @param position — מיקום הפריט ברשימה (0, 1, 2...)
     */
    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        // שליפת הפיצה הספציפית לפי המיקום
        Pizza pizza = cart.get(position);

        // טעינת תמונת הפיצה לפי שם הקובץ ב-drawable
        // getIdentifier מחפש את משאב התמונה לפי שם דינמי
        int imageId = holder.itemView.getContext().getResources()
                .getIdentifier(pizza.getImageName(), "drawable",
                        holder.itemView.getContext().getPackageName());
        holder.ivPizzaImage.setImageResource(imageId);

        // מילוי שאר הנתונים
        holder.tvPizzaName.setText(pizza.getName());
        holder.tvPizzaPrice.setText("₪" + pizza.getPrice());
        holder.tvPizzaSize.setText(pizza.getSize());
        holder.tvPizzaExtras.setText(pizza.getExtras().toString());

        // כפתור מחיקה — מסיר את הפיצה מהעגלה ומעדכן את הרשימה
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getAdapterPosition מחזיר את המיקום הנוכחי של הפריט
                // (position עלול להיות לא מעודכן אחרי מחיקות)
                int pos = holder.getAdapterPosition();
                cart.remove(pos);

                // עדכון ה-RecyclerView שפריט נמחק — מציג אנימציית הסרה
                notifyItemRemoved(pos);

                // הודעה ל-cart2 שהעגלה השתנה — כדי שיעדכן את הסכום הכולל
                listener.onCartChanged();
            }
        });
    }

    /**
     * getItemCount — מחזיר את מספר הפיצות בעגלה.
     * ה-RecyclerView קורא לפונקציה זו כדי לדעת כמה פריטים להציג.
     */
    @Override
    public int getItemCount() {
        return cart.size();
    }
}