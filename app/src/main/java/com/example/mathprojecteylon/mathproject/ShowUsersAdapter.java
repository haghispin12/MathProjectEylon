package com.example.mathprojecteylon.mathproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojecteylon.R;

import java.util.ArrayList;

public class ShowUsersAdapter extends RecyclerView.Adapter
            <ShowUsersAdapter.UserViewHolder > {
    private ArrayList<User> usersList;
    private InterOnItemClickListener listener;

    // פעולה בונה
    public ShowUsersAdapter(ArrayList<User> users, InterOnItemClickListener listener) {
        this.usersList = users;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(usersList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName;
        TextView tvUserScore;
        ImageView ivUserImg;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserN);
            tvUserScore = itemView.findViewById(R.id.tvScore);
            ivUserImg = itemView.findViewById(R.id.ivImage);
        }

        public void bind(final User item, final InterOnItemClickListener
                listener) {
            tvUserName.setText(item.getName());
            ivUserImg.setImageBitmap(item.getBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
