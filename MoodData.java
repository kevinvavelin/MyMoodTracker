package com.example.android.moodtracker;


public class MoodData {

    private int ID_;
    private int MOOD;
    private String COMMENT;
    private String WHEN_;

    public MoodData(int ID_, int MOOD, String COMMENT, String WHEN_) {
        this.setId_(ID_);
        this.setMood(MOOD);
        this.setComment(COMMENT);
        this.setWhen_(WHEN_);
    }

    public String getComment() {
        return COMMENT;
    }

    private void setComment(String comment) {
        this.COMMENT = comment;
    }

    public int getId_() {
        return ID_;
    }

    public void setId_(int id_) {
        this.ID_ = id_;
    }

    public int getMood() {
        return MOOD;
    }

    public void setMood(int mood) {
        this.MOOD = mood;
    }

    public String getWhen_() {
        return WHEN_;
    }

    public void setWhen_(String when_) {
        this.WHEN_ = when_;
    }




    @Override
    public String toString() {

        return ID_ + " : " + MOOD + " : " + COMMENT + " : " + WHEN_;


    }

}