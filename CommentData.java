package com.example.android.moodtracker;

import java.util.Date;

public class CommentData {

    private int idComment;
    private String comment;
    private Date when;

    public CommentData(int idComment, String comment, Date when) {
        this.setIdComment (idComment);
        this.setComment(comment);
        this.setWhen(when);
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }



    @Override
    public String toString() {
        return idComment + " : " + comment + " at " +  when.toString();
    }
}

