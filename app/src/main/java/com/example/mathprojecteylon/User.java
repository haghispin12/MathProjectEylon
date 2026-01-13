package com.example.mathprojecteylon;

public class User {
    private String Name;
    private int score;
    private int rating;


public User (String x){
 this.Name=x;
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
}
