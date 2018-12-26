package com.example.android.moodtracker;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.nfc.Tag;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {


    private static final String TAG = "Main Activity";
    private final static int DElTA_MIN = 50;

    int compteur = 0;
    private ImageView mWelcomeSmiley;
    private ImageButton mHistory;
    private TextView mCounter;
    private ImageButton mComment;
    private RelativeLayout mRelative;
    private int mMood;
    private TextView mCommentForTest;
    private DatabaseManager databaseManager;

    private TextView mMoodValueForTest;

    private TextView commentForTest;


    public static String userInputValue;
    public static String comment;
    public static int moodValue = 3 ;

    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);




    private GestureDetector mGestureDetector;

    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gestureDetector = new SwipeGestureDetector(this);


        mWelcomeSmiley = findViewById(R.id.welcome_smiley);
        mCounter = findViewById(R.id.counter);
        mHistory = findViewById(R.id.history);
        mComment = findViewById(R.id.comment);
        mRelative = findViewById(R.id.relative);


        //Findview for test
//        mCommentForTest = (TextView) findViewById(R.id.commentForTest);
//        mMoodValueForTest = findViewById(R.id.moodValueForText);

        //mHistory.setOnTouchListener(this);
        mGestureDetector = new GestureDetector(this, this);

        //mComment.setOnTouchListener(this);
        mWelcomeSmiley.setOnTouchListener(this);


        mWelcomeSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // compteur = ++compteur;
                // mCounter.setText(Integer.toString(compteur));
                // System.out.println(compteur);


            }
        });



        // CLIC SUR HISTORY
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String today = simpleDateFormat.format(new Date());


//                View view = LayoutInflater.from(getApplication()).inflate(R.layout.activity_history, null);
//                mMoodValueForTest =  view.findViewById(R.id.moodValueForText);

                databaseManager.insertMood (moodValue, userInputValue, today);

//                List<MoodData> moodDataList = databaseManager.readTop7();
//                for (MoodData moodData : moodDataList) {
//                    mMoodValueForTest.append(moodData.toString() + "\n");
//                }
//                databaseManager.close();


                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);


            }
        });


        databaseManager = new DatabaseManager(this);

        // CLIC SUR COMMENT - OPENS ALERT DIALOG - UserImputValue is the Input ( To see how to store it)
        mComment.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

 //               mMoodValueForTest = findViewById(R.id.moodValueForText);


                final AlertDialog.Builder inputAlert = new AlertDialog.Builder(context);
                inputAlert.setTitle("un commentaire à faire?");
                inputAlert.setMessage("entrez votre commentaire et cliquez sur valider");
                final EditText userInput = new EditText(context);
                inputAlert.setView(userInput);

                inputAlert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userInputValue = userInput.getText().toString().trim();

                        String today = simpleDateFormat.format(new Date());


//                        View view = LayoutInflater.from(getApplication()).inflate(R.layout.activity_history, null);
//                        mMoodValueForTest =  view.findViewById(R.id.moodValueForText);

                       databaseManager.insertMood (moodValue, userInputValue, today);

//                        List<MoodData> moodDataList = databaseManager.readTop7();
//                        for (MoodData moodData : moodDataList) {
//                            mMoodValueForTest.append(moodData.toString() + "\n");

//                        }
//                        databaseManager.close();

                     Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                       startActivity(historyActivityIntent);

                          Log.i("DATABASE", "insertCommand invoked");
                    }
                });
                inputAlert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = inputAlert.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        mGestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(TAG, "Ondown : called");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "OnShowPress: called");


    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "OnSingleTap: called");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "OnScroll: called");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "OnLongPress: called");

    }

    @Override

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "OnFling: called");

        float DeltaY = e2.getY() - e1.getY();

        if (Math.abs(DeltaY) > DElTA_MIN) {
            if (DeltaY > 0) {
                Log.d(TAG, "OnFling: up");
                mCounter.setText("up");
                moodValue = ++moodValue;
                if (moodValue >= 4){
                    moodValue = 4;
                }
                mMood = moodValue;
                System.out.println(mMood);
                onUpSwipe();
                swipeCase();


                return true;

            } else {
                Log.d(TAG, "OnFling: down");
                mCounter.setText("down");
                moodValue = --moodValue;
                if (moodValue <= 0){
                    moodValue = 0;
                }
                mMood = moodValue;
                System.out.println(mMood);
                onDownSwipe();
                swipeCase();

                return true;
            }
        }


        return false;
    }

    private void onUpSwipe() {

        mCounter.setText("UpSwipe");
        if (moodValue == 4 ){
            Toast.makeText(this, "You cannot be more Happy than That !", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void onDownSwipe() {
        mCounter.setText("DownSwipe");
        if (moodValue == 0 ){
            Toast.makeText(this, "You cannot be more Disappointed !", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void swipeCase(){



        switch (moodValue){

            case 0: //Smiley Sad
                mRelative.setBackgroundResource(R.color.color_sad);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_sad);
                break;

            case 1: //Smiley Disappointed
                mRelative.setBackgroundResource(R.color.color_disappointed);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_disappointed);
                break;

            case 2: //Smiley Normal
                mRelative.setBackgroundResource(R.color.color_normal);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_normal);
                break;

            case 3: //Smiley Happy
                mRelative.setBackgroundResource(R.color.color_happy);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_happy);
                break;

            case 4: //Smiley Super_Happy
                mRelative.setBackgroundResource(R.color.color_super_happy);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_super_happy);
                break;


        }

    }


}




