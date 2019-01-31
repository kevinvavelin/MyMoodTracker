package com.example.android.moodtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {


    private static final String TAG = "Main Activity";
    private final static int DElTA_MIN = 50;


    private ImageView mWelcomeSmiley;
    private ImageButton mHistory;
    private ImageButton mComment;
    private RelativeLayout mRelative;
    private DatabaseManager databaseManager;


    public static String userInputValue;
    public static String comment;
    public static int moodValue = 3 ;

    String pattern = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    private GestureDetector mGestureDetector;

    private Context context = this;
	
	private MediaPlayer mMediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWelcomeSmiley = findViewById(R.id.welcome_smiley);
        mHistory = findViewById(R.id.history);
        mComment = findViewById(R.id.comment);
        mRelative = findViewById(R.id.relative);


        mGestureDetector = new GestureDetector(this, this);


        mWelcomeSmiley.setOnTouchListener(this);


        mWelcomeSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                           }
        });



        // CLIC SUR HISTORY
        mHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);

            }
        });


        databaseManager = new DatabaseManager(this);

        // CLIC SUR COMMENT - OPENS ALERT DIALOG - UserImputValue is the Input ( To see how to store it)
        mComment.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


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

                       databaseManager.insertMood (moodValue, userInputValue, today);

                 //         Log.i("DATABASE", "insertCommand invoked");

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
     //   Log.d(TAG, "OnFling: called");

        float DeltaY = e2.getY() - e1.getY();

        if (Math.abs(DeltaY) > DElTA_MIN) {
            if (DeltaY < 0) {
             //   Log.d(TAG, "OnFling: up");

                moodValue = ++moodValue;
                if (moodValue >= 4){
                    moodValue = 4;
                }
                onUpSwipe();
                swipeCase();
                String today = simpleDateFormat.format(new Date());
                databaseManager.insertMood (moodValue, userInputValue, today);

                return true;

            } else {
             //   Log.d(TAG, "OnFling: down");

                moodValue = --moodValue;
                if (moodValue <= 0){
                    moodValue = 0;
                }
                onDownSwipe();
                swipeCase();
                String today = simpleDateFormat.format(new Date());
                databaseManager.insertMood (moodValue, userInputValue, today);


                return true;
            }
        }


        return false;
    }

    private void playMedia() {

        mMediaPlayer = MediaPlayer.create(this, R.raw.beep_sonore);
		try {
			mMediaPlayer.resume();
			 mMediaPlayer.start();
		}catch(Exception exception) {
			System.Out.Println(exception.getMessage());
		}
    }
	
	private void stopMedia() {
		if(mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
	}

    private void onUpSwipe() {
        if (moodValue == 4 ){
            Toast.makeText(this, "You can't be Happier ! Enjoy !", Toast.LENGTH_SHORT).show();
        }

    }

    private void onDownSwipe() {
        if (moodValue == 0 ){
            Toast.makeText(this, "You can't be Sadder, Things will be better Soon !", Toast.LENGTH_SHORT).show();
        }

    }

    public void swipeCase(){

		stopMedia();

        switch (moodValue){

            case 0: //Smiley Sad
                mRelative.setBackgroundResource(R.color.color_sad);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_sad);
                playMedia();
                break;

            case 1: //Smiley Disappointed
                mRelative.setBackgroundResource(R.color.color_disappointed);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_disappointed);
                playMedia();
                break;

            case 2: //Smiley Normal
                mRelative.setBackgroundResource(R.color.color_normal);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_normal);
                playMedia();
                break;

            case 3: //Smiley Happy
                mRelative.setBackgroundResource(R.color.color_happy);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_happy);
                playMedia();
                break;

            case 4: //Smiley Super_Happy
                mRelative.setBackgroundResource(R.color.color_super_happy);
                mWelcomeSmiley.setImageResource(R.drawable.smiley_super_happy);
                playMedia();
                break;


        }

    }


}




