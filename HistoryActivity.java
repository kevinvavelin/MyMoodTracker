package com.example.android.moodtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    final String myList = "";
    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    private TextView mMoodValueForTest;
    private DatabaseManager databaseManager;
    private TextView commentForTest;
    private String userInputValue;




//    static String[][] tableStatic = new String[][]{{ "0", "1", "2", "3", "4"},
//            {R.layout.sad_layout, R.layout.disappointed_layout, R.layout.normal_layout, R.layout.happy_layout, R.layout.super_happy_layout}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("History");
        databaseManager = new DatabaseManager(this);


//        private void moodStatic () {
//
//            Mood superHappyMood = new Mood(R.drawable.smiley_super_happy, R.color.color_super_happy,"4");
//            Mood happyMood = new Mood(R.layout.list_items_row)
//
//        }

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

//
//    }
//
//    private void populateListView() {
//        Cursor cursor = databaseManager.getMoodComment();
//
//        String[] lesMood = new String[]{DatabaseManager.MOOD};
//        String[] lesComment = new String[]{DatabaseManager.COMMENT};
//
//        int[] toTheMood = new int[]{R.id.numberOfTheDay};
//        int[] toTheComment = new int[]{R.id.textOfTheDay};
//
//
////            final String [] mood = {"0","1","2","3","4"};
////            final int [] moodLayout = {R.layout.sad_layout, R.layout.disappointed_layout, R.layout.normal_layout, R.layout.happy_layout, R.layout.super_happy_layout};
////            final int [] color =
//
//
//        String[] fromTheDb = new String[]{DatabaseManager.COMMENT, DatabaseManager.MOOD};
//        int[] toTheListView = new int[]{R.id.textOfTheDay, R.id.numberOfTheDay};
//
//        final SimpleCursorAdapter myCursorAdapter;
//
//
//        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_items_row, cursor, fromTheDb, toTheListView, 0);
//        final ListView myList = (ListView) findViewById(R.id.display_listview);
//
//        myCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
//                                          public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
//                                              // if this holds true, then you know that you are currently binding text to
//                                              //                                                  the TextView with id "R.id.alarmName"
//                                                  if (myList.getId() == R.id.) {
//                                                      final int mood = cursor.getColumnIndex("mood");
//                                                      final int color = cursor.getInt(mood);
//
//                                                  switch (color) {
//                                                      case 0:
//                                                          ((TextView) view).setTextColor(Color.RED);
//                                                          break;
//                                                      case 1: /* ... */
//                                                          break;
//                                                      case 2: /* ... */
//                                                          break;
//                                                      /* etc. */
//                                                  }
//                                                  return true;
//                                              }
//                                              return false;
//
//                                              myList.setAdapter(myCursorAdapter);
//
//                                          }
//                                      });
//    }
//}


        // myList.setAdapter(myCursorAdapter);

//            myList.setBackgroundColor(getResources().getColor(R.color.color_sad));
//            myList.W((R.dimen.dimension_sad));


//    public long[] arrayFromCursor(Cursor cursor) {
//
//        int length = cursor.getCount();
//        long[] array = new long[length];
//
//        if (cursor.moveToFirst()) {
//            for (int i = 0; i < length; i++) {
//                array[i] = cursor.getLong(cursor.getColumnIndex("mood"));
//                cursor.moveToNext();
//            }
//        }
//        return array;
//    }

    }

    private void populateListView() {
        // Cursor cursor = databaseManager.getMoodComment();



        final List<MoodData> moodDataList = databaseManager.readTop7();
        MoodHistoryAdapter moodHistoryAdapter = new MoodHistoryAdapter(getBaseContext(), moodDataList);
        final ListView myList = findViewById(R.id.display_listview);
        myList.setAdapter(moodHistoryAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("clic", "click sur Icon " + position + " " +id );

               String iconOrNot = moodDataList.get(position).COMMENT;
         //       Log.i("datas", "" + iconOrNot );
               if (iconOrNot != null) {
                    Toast.makeText(HistoryActivity.this, " " + iconOrNot, Toast.LENGTH_SHORT).show();
               } else {
                }
            }
        });}

        }





//            String[] fromTheDb = new String[]{DatabaseManager.COMMENT, DatabaseManager.MOOD};
//
//            int[] toTheListView = new int[]{R.id.textOfTheDay, R.id.numberOfTheDay};
//            SimpleCursorAdapter myCursorAdapter;
//            myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_items_row, cursor, fromTheDb, toTheListView, 0);
//
//            ListView myList = (ListView) findViewById(R.id.display_listview);
//
//            myList.setAdapter(myCursorAdapter);


//   private void commentOrNot (String [] fromTheDb) {
//        if (fromTheDb != null) {
//            fromTheDb = new String[] {DatabaseManager.COMMENT , DatabaseManager.MOOD}
//        } else {
//            fromTheDb = new String[] {DatabaseManager.COMMENT , DatabaseManager.MOOD};

//       }

//    //switch pour définir les couleurs et la taille du layout selon le mood
//        private void displayMood(Mood mood, RelativeLayout layout) {
//            Display display = getWindowManager().getDefaultDisplay();
//            int width = 0;
//        switch (mood) {
//            case GREAT:
//                layout.setBackgroundColor(getColor(R.color.light_sage));
//                width = (display.getWidth() / 1);
//                break;
//            case GOOD:
//                layout.setBackgroundColor(getColor(R.color.banana_yellow));
//                width = (display.getWidth() / 2);
//                break;
//            case NORMAL:
//                layout.setBackgroundColor(getColor(R.color.warm_grey));
//                width = (display.getWidth() / 3);
//                break;
//            case BAD:
//                layout.setBackgroundColor(getColor(R.color.cornflower_blue_65));
//                width = (display.getWidth() / 4);
//                break;
//            case REALLY_BAD:
//                layout.setBackgroundColor(getColor(R.color.faded_red));
//                width = (display.getWidth() / 5);
//                break;
//        }


