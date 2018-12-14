package com.example.android.moodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import static com.example.android.moodtracker.MainActivity.comment;
import static com.example.android.moodtracker.MainActivity.moodValue;
import static com.example.android.moodtracker.MainActivity.userInputValue;


public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lescommentaires.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        String strSql = "create table T_mood ("
//                + "  idMood integer primary key autoincrement,"
//                + "  mood integer not null,"
//                + "  when_ integer not null"
//                + ")";
//
//
//        String strSqlComment = "create table T_comment ("
//                + "  idComment integer primary key autoincrement,"
//                + "  comment text not null,"
//                + "  when_ integer not null"
//                + ")";
//
//        db.execSQL(strSql);
//        Log.i("DATABASE", "OnCreate invoked");
//        db.execSQL(strSqlComment);
//        Log.i("DATABASE", "OnCreateT_Comment invoked");


        String strSql = "CREATE TABLE T_mood ("
                + "   id_ INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "   mood INTEGER NOT NULL,"
                + "   comment TEXT ,"
                + "   when_ TEXT NOT NULL"
                + ")";
        db.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSql = "drop table if exists T_mood";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.i("DATABASE", "OnUpgrade invoked");

    }

    public void insertMood(int moodValue, String userInputValue, String today) {

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        today = simpleDateFormat.format(new Date());
        String todayIsToday = todayIsToday();


        boolean result;

        if (result = today.equals(todayIsToday)) {
            // # Todo

            //String strSql = "DELETE FROM T_mood WHERE when_ = when_ OR when_  ";
//            String strSql = "UPDATE T_mood SET mood = mood, comment = comment WHERE when_ = when_";
//            this.getWritableDatabase().execSQL(strSql);
                //UPDATE ROW
//                SQLiteDatabase db = this.getWritableDatabase();
//                ContentValues contentValues = new ContentValues();
//                contentValues.put("mood", mood );
//                contentValues.put("comment", comment);
//                db.update("T_mood", contentValues, "when_ = 'today'", null );
//                db.close();


               updateRow(moodValue, userInputValue, today);

//            // #Todo
//            String strSql = "insert into T_mood (mood, comment, when_) values ("
//                    + mood + ",'" + comment + "','" + simpleDateFormat.format(new Date()) + "')";
//
//            this.getWritableDatabase().execSQL(strSql);
//            Log.i("DATABASE", "insertMood invoked");

        } else {
            // #Todo
            String strSql = "insert into T_mood (mood, comment, when_) values ("
                    + moodValue + ",'" + userInputValue + "','" + simpleDateFormat.format(new Date()) + "')";

            this.getWritableDatabase().execSQL(strSql);
            Log.i("DATABASE", "insertMood invoked");
        }



//        boolean result;
//        if (result = today.equals(today)) {
//
//            //String strSql = "UPDATE T_mood SET mood = mood, comment = 'comment' VALUES (" + mood + ",'" + comment + "')";
//            String strSql = "UPDATE T_mood SET mood = mood, comment = 'comment' WHERE _id=when_);
//            Cursor cursor = this.getWritableDatabase().rawQuery(strSql, null);
//            cursor.moveToFirst();
//            cursor.close();
//        } else {

//        //comment = comment.replace("'", "''");
//        String strSql = "insert into T_mood (mood, comment, when_) values ("
//                + mood + ",'" + comment + "','" + simpleDateFormat.format(new Date()) + "')";
//
//        this.getWritableDatabase().execSQL(strSql);
//        Log.i("DATABASE", "insertMood invoked");
//    }


//    private void updateMood(String mood, String new_Mood) {
//
//
 }



    public List<MoodData> readTop7() {
        List<MoodData> moodDataList = new ArrayList<>();

        //Request SQL
        String strSql = "select * from T_mood order by _id desc limit 7";


        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null);
        //Cursor cursor = this.getReadableDatabase().query("T_mood", new String[]{"_id", "mood", "comment", "when_"}, null, null, null, null, "when_ desc", "7");
        cursor.moveToFirst();
        while ( ! cursor.isAfterLast()) {
            // MoodData moodData = new MoodData (cursor.getString(0));
            MoodData moodData = new MoodData(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            moodDataList.add(moodData);
            cursor.moveToNext();
        }
        cursor.close();

        return moodDataList;

    }

    public String todayIsToday() {
        String todayIsToday = "SELECT when_ FROM T_mood order by _id desc limit 1";


        Cursor cursor = this.getReadableDatabase().rawQuery(todayIsToday, null);
        cursor.moveToFirst();
        String dateAlreadyExists = null;
        while (!cursor.isAfterLast()) {
            dateAlreadyExists = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();

        todayIsToday = dateAlreadyExists;

        return todayIsToday;
    }

    public boolean updateRow(int moodValue, String userInputValue, String today) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mood", moodValue );
        contentValues.put("comment", userInputValue);
        db.update("T_mood", contentValues, "when_ = '" + today + "'", null);

        return true;
    }


}