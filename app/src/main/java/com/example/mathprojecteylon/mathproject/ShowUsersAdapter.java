package com.example.mathprojecteylon.mathproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathprojecteylon.R;

import java.util.ArrayList;

public class ShowUsersAdapter extends RecyclerView.Adapter
        <ShowUsersAdapter.UserViewHolder > {
    private ArrayList<User> usersList;
    private InterOnItemClickListener listener;

    // פעולה בונה
    public ShowUsersAdapter(ArrayList<User> usersList,
                            InterOnItemClickListener listener) {
        this.usersList = usersList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

}
