package com.example.android.moodtracker;


public class MoodData {

    private int id_;
    private int mood;
    private String comment;
    private String when_;

    public MoodData(int id_, int mood, String comment, String when_) {
        this.setId_(id_);
        this.setMood(mood);
        this.setComment(comment);
        this.setWhen_(when_);
    }

    public String getComment() {
        return comment;
    }

    private void setComment(String comment) {
        this.comment = comment;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getWhen_() {
        return when_;
    }

    public void setWhen_(String when_) {
        this.when_ = when_;
    }




    @Override
    public String toString() {

        return id_ + " : " + mood + " : " + comment + " : " + when_;


    }

}