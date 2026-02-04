package com.example.mathprojecteylon;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;


public class ShowOnUsers extends Fragment {
private User user;
private TextView score;
private TextView rating;
private EditText us;
private Uri uri;
private Button addPicture;
private ImageView showPic;
private Button addUser;
private ArrayList<User> Array;

    ActivityResultLauncher<Intent>startCamera=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                 if(result.getResultCode()== RESULT_OK){
                     showPic.setImageURI(uri);
                     user.setUri(uri);
                 }

                }

            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment, container, false);
        rating=view.findViewById(R.id.TvRa);//מבצע הכנסה של הכפתור לקוד
        score=view.findViewById(R.id.TvSc);
        us=view.findViewById(R.id.EdUs);
        addPicture=view.findViewById(R.id.addP);
        showPic=view.findViewById(R.id.ImageV);
        addUser=view.findViewById(R.id.addUS);


        String myUser1  = getArguments().getString("myUser");//מביא נתונים
        Gson gson = new Gson();
        user= gson.fromJson(myUser1, User.class);
        //user.setRating(myUser.getRating());
        rating.setText("rating:"+user.getRating());
        score.setText("score:"+user.getScore());
        us.setText("name:"+user.getName());
        addUser.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                       dbAddUSer();
                                       }
                                   });


                addPicture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        ContentValues values = new ContentValues();

                        values.put(MediaStore.Images.Media.TITLE, "New Picture");
                        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

                        uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);// הגדרת כותרת והסבר לתמונה במשתנה uri

                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// הגדרת intent עבור צילום והעמסת uri עליו באמצעות key
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                        startCamera.launch(cameraIntent);// שימוש באובייקט שבנינו להאזנה על מנת להפעיל את המצלמה


                    }
                });
        return view;



    }

    public void dbAddUSer(){
        DBHelper db=new DBHelper(getActivity());
        long id=db.insert (user,getActivity());
        int x=0;
    }
    public User dbSelectUsers(){

    }
}