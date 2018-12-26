package com.example.android.moodtracker;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.android.moodtracker.MainActivity.moodValue;
import static com.example.android.moodtracker.MainActivity.comment;


public class HistoryActivity extends AppCompatActivity {

    private TextView mMoodValueForTest;
    private DatabaseManager databaseManager;
    private TextView commentForTest;
    private String userInputValue;

    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("Northern' characters");
        databaseManager = new DatabaseManager(this);
           populateListView();

//        // Find the View
//        ListView lv = (ListView) findViewById(R.id.display_listview);
//
//        // Create a string Array to listView
//        String [] laliste = {"Pierre", "Paul","Jacques"};
//       // List<MoodData> moodDataList = databaseManager.readTop7();
//
//        // Create adapter to show this array like a list
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_items_row, laliste);
//
//        // put this adapter to the listview
//        lv.setAdapter(adapter);

    //



//        mMoodValueForTest = findViewById(R.id.moodValueForText);
//        commentForTest = findViewById(R.id.commentForTest);
//
//        databaseManager = new DatabaseManager(this);
//
//
//
//
////        databaseManager.insertMood(moodValue, userInputValue, simpleDateFormat.format(new Date()));
////        // mMoodValueForTest.setText(" a voir " + String.valueOf(moodValue));
////
//
//
//        List<MoodData> moodDataList = databaseManager.readTop7();
//        for (MoodData moodData : moodDataList) {
//            mMoodValueForTest.append(moodData.toString() + "\n");
//        }
//
////
//
           databaseManager.close();
//  }



    }



    private void populateListView () {
        Cursor cursor = databaseManager.getMoodComment();
        String [] fromTheDb = new String[] {DatabaseManager.COMMENT , DatabaseManager.MOOD};
        int [] toTheListView = new int[] {R.id.textOfTheDay, R.id.numberOfTheDay};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_items_row, cursor, fromTheDb, toTheListView,0);
        ListView myList = (ListView) findViewById(R.id.display_listview);
        myList.setAdapter(myCursorAdapter);


    }
}