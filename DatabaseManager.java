package com.example.android.moodtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseManager extends SQLiteOpenHelper {

    // NAME of the Database
    private static final String DATABASE_NAME = "loscincocommentaires.db";
    // DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    public static final String COMMENT = "comment";
    public static final String MOOD = "mood";
    public static final String WHEN_ = "when_";



    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        // CREATION OF THE TABLE T_MOOD with 4 Columns - Comment can be null
        String strSql = "CREATE TABLE T_mood ("
                + "   _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "   mood INTEGER NOT NULL,"
                + "   comment TEXT ,"
                + "   when_ TEXT NOT NULL"
                + ")";
        db.execSQL(strSql);

    }

    @Override
    // ON UPGRADE, we drop the table and create an other one
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String strSql = "drop table if exists T_mood";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.i("DATABASE", "OnUpgrade invoked");

    }
// Important Method allows to insert datas (Mood and comment) and Update them
    public void insertMood(int moodValue, String userInputValue, String today) {

        // Makes the format requested for the date to insert eg. '13-12-18'
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        // today is the date of the day, todayisToday is the result of the boolean method todayIsToday - see below
        today = simpleDateFormat.format(new Date());
        String todayIsToday = todayIsToday();


        //boolean result;
        // If result is true, it means that the date already exists and the update of the row id launched, if not, just insert the data
        if (today.equals(todayIsToday)) {

            // Method includes moodValue and userInputValue, while today is the date
               updateRow(moodValue, userInputValue, today);

        } else {
            // If Result is false, it means that the date doesn't exist, so we insert a new Row in the database
            String strSql = "insert into T_mood (mood, comment, when_) values ("
                    + moodValue + ",'" + userInputValue + "','" + simpleDateFormat.format(new Date()) + "')";
            // In DatabaseManager (this), we write (getWritableDatabase) and execute the String "strSql" to write into the database
            this.getWritableDatabase().execSQL(strSql);

        }


 }


        // Method List called readTop7, allows to get a list with the 7 latest data saved
    public List<MoodData> readTop7() {
        List<MoodData> moodDataList = new ArrayList<>();

        //Request SQL
        
//         String pattern = "dd-MM-yyyy";
//         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

//         // today is the date of the day, todayisToday is the result of the boolean method todayIsToday - see below
//         today = simpleDateFormat.format(new Date());
        
        // todayDateDelta => new Date().day - T_mood.date.day doit retourner un nombre <= 7
        // todayDateDelta => new Date().day(-7)
        
        // select * from T_mood where T_mood.date <= todayDateDelta

          String strSql = "select * from (select * from T_mood order by _id desc limit 7 offset 1) as strSql order by _id asc";
        //String strSql = "select * from (select * from T_mood order by _id desc limit 7) as strSql order by _id asc";


        // Creation of a Cursor called cursor. It will read in the database according to the SQL request strSql - rawquery
            Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null);
            // Put the cursor at before the first line first row
            cursor.moveToFirst();
            // While the cursor did not passed the last data, go on my son !
            while ( ! cursor.isAfterLast()) {
            // Collection in moodData of the datas. Here according to the Table T_mood columns. ColumnIndex 0 = ID_, ColumnIndex 1 = mood/MoodValue, ColumnIndex 2 = Comment/UserInputValue, ColumnIndex 3 = When_/Today
            MoodData moodData = new MoodData(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3));
            moodDataList.add(moodData);
            cursor.moveToNext();
        }
        cursor.close();

        return moodDataList;

    }
        // Is the date of the day already exists in the database? True/false
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
    // Update of the Row, we do change only the MoodValue and userInputValue WHERE when_ = today
    public boolean updateRow(int moodValue, String userInputValue, String today) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("mood", moodValue );
        contentValues.put("comment", userInputValue);
        db.update("T_mood", contentValues, "when_ = '" + today + "'", null);

        return true;
    }


}
