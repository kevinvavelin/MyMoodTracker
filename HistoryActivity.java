package com.example.android.moodtracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import static com.example.android.moodtracker.MainActivity.moodValue;
import static com.example.android.moodtracker.MainActivity.userInputValue;

public class HistoryActivity extends AppCompatActivity {

    private TextView mMoodValueForTest;
    private DatabaseManager databaseManager;
    private TextView commentForTest;
    private String userInputValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("Northern' characters");

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new Myadapter());


        mMoodValueForTest = findViewById(R.id.moodValueForText);
        commentForTest = findViewById(R.id.commentForTest);

        databaseManager = new DatabaseManager(this);


        databaseManager.insertMood(moodValue, userInputValue, (int) new Date().getTime());
        // mMoodValueForTest.setText(" a voir " + String.valueOf(moodValue));

        List<MoodData> moodDataList = databaseManager.readTop7();
        for (MoodData moodData : moodDataList) {
            mMoodValueForTest.append(moodData.toString() + "\n");
        }

//        List<CommentData> commentDataList = databaseManager.commentTop7();
//        for (CommentData commentData : commentDataList) {
//            commentForTest.append(commentData.toString() + "\n");
//        }

        databaseManager.close();
    }


}