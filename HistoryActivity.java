package com.example.android.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("My History");
        databaseManager = new DatabaseManager(this);

        populateListView();

        databaseManager.close();

    }

    private void populateListView() {

        // Pick up the List from the DatabaseManager from the readTop7 methos
        final List<MoodData> moodDataList = databaseManager.readTop7();
        MoodHistoryAdapter moodHistoryAdapter = new MoodHistoryAdapter(getBaseContext(), moodDataList);
        final ListView myList = findViewById(R.id.display_listview);
        myList.setAdapter(moodHistoryAdapter);

        // Listener on the List_item to act to be able to display the toast with the corresponding comments
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("clic", "click sur Icon " + position + " " + id);

                String iconOrNot = moodDataList.get(position).COMMENT;
                //       Log.i("datas", "" + iconOrNot );
                if (iconOrNot != null) {
                    Toast.makeText(HistoryActivity.this, " " + iconOrNot, Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        });


    }

}

