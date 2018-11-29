package com.example.android.moodtracker;

import java.util.Date;

public class MoodData {

    private int idMood;
    private int mood;
    private Date when;
    private String comment;

    public MoodData(int idMood, int mood, String comment, Date when) {
        this.setIdMood(idMood);
        this.setMood(mood);
        this.setWhen(when);
        this.setComment(comment);
    }

    private void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public int getIdMood() {
        return idMood;
    }

    public void setIdMood(int idMood) {
        this.idMood = idMood;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }


    @Override
    public String toString() {

        return idMood + " : " + mood + " : " + comment + " : " + when ;
    }
}
