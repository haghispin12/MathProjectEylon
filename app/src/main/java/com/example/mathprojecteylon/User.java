package com.example.mathprojecteylon;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    private String Name;
    private int score;
    private int rating;
    private long id;
    private Uri uri;
    private Bitmap bitmap;


public User (String x){
 this.Name=x;
}

    public User(long id, String name, int rating, Bitmap bitmap, int score) {
    this.id=id;
    this.Name=name;
    this.rating=rating;
    this.bitmap=bitmap;
    this.score=score;
    }

    public void setScore(int score) {
        this.score = score;

    }
    
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return score;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
