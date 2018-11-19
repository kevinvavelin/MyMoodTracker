package com.example.android.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import static com.example.android.moodtracker.MainActivity.moodValue;

public class HistoryActivity extends AppCompatActivity {

    private TextView mMoodValueForTest;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mMoodValueForTest = findViewById(R.id.moodValueForText);
        databaseManager = new DatabaseManager( this );


        databaseManager.insertMood(moodValue, (int) new Date().getTime());
        // mMoodValueForTest.setText(" a voir " + String.valueOf(moodValue));

        List<MoodData> moodDataList = databaseManager.readTop7();
        for (MoodData moodData  : moodDataList){
            mMoodValueForTest.append(moodData.toString() + "\n");
        }
        databaseManager.close();
    }
}
