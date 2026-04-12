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
public class cartAdapter extends RecyclerView.Adapter<cartAdapter.CartViewHolder> {
    private ArrayList<Pizza> cart;
    private OnCartChangedListener listener;

    public interface OnCartChangedListener {
        void onCartChanged();
    }

    public cartAdapter(ArrayList<Pizza> cart, OnCartChangedListener listener) {
        this.cart = cart;
        this.listener = listener;
    }
    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPizzaImage;
        TextView tvPizzaName;
        TextView tvPizzaPrice;
        TextView tvPizzaSize;
        TextView tvPizzaExtras;
        Button btnDelete;
        public CartViewHolder(View itemView) {
            super(itemView);
            ivPizzaImage = itemView.findViewById(R.id.ivPizzaImage);
            tvPizzaName = itemView.findViewById(R.id.tvPizzaName);
            tvPizzaPrice = itemView.findViewById(R.id.tvPizzaPrice);
            tvPizzaSize = itemView.findViewById(R.id.tvPizzaSize);
            tvPizzaExtras = itemView.findViewById(R.id.tvPizzaExtras);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item2, parent, false);
        return new CartViewHolder(view);
    }
    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Pizza pizza = cart.get(position);
        int imageId = holder.itemView.getContext().getResources()
                .getIdentifier(pizza.getImageName(), "drawable",
                        holder.itemView.getContext().getPackageName());
        holder.ivPizzaImage.setImageResource(imageId);
        holder.tvPizzaName.setText(pizza.getName());
        holder.tvPizzaPrice.setText("₪" + pizza.getPrice());
        holder.tvPizzaSize.setText(pizza.getSize());
        holder.tvPizzaExtras.setText(pizza.getExtras().toString());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                cart.remove(pos);
                notifyItemRemoved(pos);
                listener.onCartChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return cart.size();
    }
}