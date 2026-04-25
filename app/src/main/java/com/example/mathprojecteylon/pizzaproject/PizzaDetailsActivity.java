package com.example.mathprojecteylon.pizzaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mathprojecteylon.R;

import java.util.ArrayList;

/**
 * מסך פרטי הפיצה.
 * מאפשר ללקוח לבחור גודל ותוספות לפיצה שבחר.
 * המחיר מתעדכן בזמן אמת בהתאם לבחירות.
 */
public class PizzaDetailsActivity extends AppCompatActivity {

    // כפתורים ותצוגות
    private Button btnBack;
    private TextView tvPizzaTitle;
    private ImageView ivPizzaImage;
    private TextView tvPizzaName;
    private TextView tvPizzaPrice;

    // רדיו בטאטונים לבחירת גודל
    private RadioGroup rgSize;
    private RadioButton rbSmall;
    private RadioButton rbMedium;
    private RadioButton rbLarge;
    private RadioButton rbXLarge;

    // צ'קבוקסים לבחירת תוספות
    private CheckBox cbTomato;
    private CheckBox cbCorn;
    private CheckBox cbOlives;
    private CheckBox cbMushrooms;
    private CheckBox cbOnion;
    private CheckBox cbPepper;
    private CheckBox cbBlacklOlive;
    private CheckBox cbPineapple;
    private CheckBox cbTuna;

    private Button btnAddToCart;

    // פרטי הפיצה שהגיעו מהמסך הקודם דרך Intent
    private String pizzaName;
    private String pizzaImage;

    // מחירים
    private int basePrice;    // המחיר הבסיסי של הפיצה
    private int totalPrice;   // המחיר הסופי — בסיס + גודל + תוספות
    private int sizePrice;    // מחיר לפי גודל
    private int extrasPrice;  // סכום כל התוספות

    // רשימת התוספות שנבחרו
    private ArrayList<String> extras = new ArrayList<>();

    // הגודל שנבחר
    private String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        // חיבור המשתנים לשדות ב-XML
        btnBack = findViewById(R.id.btnBack);
        tvPizzaTitle = findViewById(R.id.tvPizzaTitle);
        tvPizzaPrice = findViewById(R.id.tvTotalPrice);
        ivPizzaImage = findViewById(R.id.ivPizzaImage);
        tvPizzaName = findViewById(R.id.tvPizzaName);
        rgSize = findViewById(R.id.rgSize);
        rbSmall = findViewById(R.id.rbSmall);
        rbMedium = findViewById(R.id.rbMedium);
        rbLarge = findViewById(R.id.rbLarge);
        rbXLarge = findViewById(R.id.rbXLarge);
        cbTomato = findViewById(R.id.cbTomato);
        cbCorn = findViewById(R.id.cbCorn);
        cbOlives = findViewById(R.id.cbOlives);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        cbOnion = findViewById(R.id.cbOnion);
        cbPepper = findViewById(R.id.cbPepper);
        cbBlacklOlive = findViewById(R.id.cbBlackOlive);
        cbPineapple = findViewById(R.id.cbPineapple);
        cbTuna = findViewById(R.id.cbTuna);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // קבלת פרטי הפיצה שהועברו מ-MainActivityPizza דרך Intent
        pizzaName = getIntent().getStringExtra("pizzaName");
        pizzaImage = getIntent().getStringExtra("pizzaImage");
        basePrice = Integer.parseInt(getIntent().getStringExtra("pizzaPrice"));

        // ברירת מחדל — גודל M במחיר הבסיסי
        size = "M";
        sizePrice = basePrice;

        // טעינת תמונת הפיצה לפי שם הקובץ
        int id = getResources().getIdentifier(pizzaImage, "drawable", getPackageName());
        ivPizzaImage.setImageResource(id);

        tvPizzaPrice.setText("₪" + basePrice);
        init();
    }

    /**
     * מגדיר את כל המאזינים לאירועים במסך
     */
    public void init() {

        // כפתור חזרה לתפריט הפיצות
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PizzaDetailsActivity.this, MainActivityPizza.class);
                startActivity(intent);
            }
        });

        // מאזין לשינוי גודל — מעדכן את המחיר בהתאם
        // S = מחיר בסיסי פחות 10, M = בסיסי, L = בסיסי פלוס 10, XL = בסיסי פלוס 20
        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbSmall) {
                    sizePrice = basePrice - 10;
                    size = "S";
                } else if (checkedId == R.id.rbMedium) {
                    sizePrice = basePrice;
                    size = "M";
                } else if (checkedId == R.id.rbLarge) {
                    sizePrice = basePrice + 10;
                    size = "L";
                } else if (checkedId == R.id.rbXLarge) {
                    sizePrice = basePrice + 20;
                    size = "XL";
                }
                // עדכון המחיר הכולל
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        // מאזיני תוספות — כל תוספת מוסיפה 5 ש"ח למחיר
        // כשמסמנים — מוסיפים לרשימת extras ולמחיר
        // כשמבטלים — מסירים מהרשימה ומורידים מהמחיר

        cbTomato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("tomato");
                } else {
                    extrasPrice -= 5;
                    extras.remove("tomato");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbCorn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("corn");
                } else {
                    extrasPrice -= 5;
                    extras.remove("corn");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbOlives.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("olives");
                } else {
                    extrasPrice -= 5;
                    extras.remove("olives");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbMushrooms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("mushrooms");
                } else {
                    extrasPrice -= 5;
                    extras.remove("mushrooms");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbPepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("pepper");
                } else {
                    extrasPrice -= 5;
                    extras.remove("pepper");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbOnion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("onion");
                } else {
                    extrasPrice -= 5;
                    extras.remove("onion");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        // אננס — עולה 6 ש"ח (יקר יותר כי זה פרי טרי)
        cbPineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 6;
                    extras.add("pineapple");
                } else {
                    extrasPrice -= 6;
                    extras.remove("pineapple");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        // טונה — עולה 8 ש"ח (יקר יותר כי זה חלבון)
        cbTuna.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 8;
                    extras.add("tunaFish");
                } else {
                    extrasPrice -= 8;
                    extras.remove("tunaFish");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        cbBlacklOlive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    extrasPrice += 5;
                    extras.add("blackOlive");
                } else {
                    extrasPrice -= 5;
                    extras.remove("blackOlive");
                }
                totalPrice = sizePrice + extrasPrice;
                tvPizzaPrice.setText("סך הכול לתשלום: ₪" + totalPrice);
            }
        });

        // כפתור הוספה לעגלה — יוצר אובייקט Pizza עם כל הבחירות ומוסיף לעגלה
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // יצירת אובייקט פיצה עם כל הפרטים שנבחרו
                Pizza pizza = new Pizza(totalPrice, pizzaName, size, extras, pizzaImage);

                // הוספת הפיצה לעגלת הקניות של הקונה המחובר
                Buyer.currentBuyer.addToCart(pizza);

                // מעבר למסך העגלה
                Intent intent = new Intent(PizzaDetailsActivity.this, cart2.class);
                startActivity(intent);
            }
        });

        // הצגת שם הפיצה במסך
        tvPizzaName.setText(pizzaName);
    }
}