package com.example.mathprojecteylon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;


public class ShowOnUsers extends Fragment {
private User user;
private TextView score;
private TextView rating;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment, container, false);
        rating=view.findViewById(R.id.TvRa);
        score=view.findViewById(R.id.TvSc);

        String myUser1  = getArguments().getString("myUser");
        Gson gson = new Gson();
        user= gson.fromJson(myUser1, User.class);
        //user.setRating(myUser.getRating());
        rating.setText(user.getRating()+"");

        return view;
    }


}