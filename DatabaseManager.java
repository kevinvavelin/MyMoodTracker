package com.example.android.moodtracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Historique.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseManager (Context context){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table T_mood ("
                      + "  idMood integer primary key autoincrement,"
                      + "  mood integer not null,"
                      + "  when_ integer not null"
                      + ")";
        db.execSQL( strSql );
        Log.i("DATABASE", "OnCreate invoked");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql = "alter table T_mood add column Etat integer";
        db.execSQL( strSql );
        Log.i("DATABASE", "OnUpgrade invoked");
    }

    public void insertMood ( int mood, int when_ ){
        String strSql = "insert into T_mood (mood, when_) values ("
                      + mood + "," + new Date().getTime() + ")";

        this.getWritableDatabase().execSQL( strSql);
        Log.i("DATABASE", "insertMood invoked");
    }

    public List<MoodData> readTop7(){
        List<MoodData> moodDataList = new ArrayList<>();

        //1ere technique
        String strSql = "select * from T_mood order by when_ Desc limit 7";
        Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null);
        cursor.moveToFirst();
        while ( ! cursor.isAfterLast()){
            MoodData moodData = new MoodData( cursor.getInt( 0 ), cursor.getInt( 1 ), new Date(cursor.getLong( 2 )));
            moodDataList.add(moodData);
            cursor.moveToNext();
        }
            cursor.close();

        return moodDataList;

    }
}
