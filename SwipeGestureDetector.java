package com.example.android.moodtracker;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SwipeGestureDetector extends GestureDetector {

    private final static int DElTA_MIN = 50;

    public SwipeGestureDetector(final MainActivity context) {
        super(context, new GestureDetector.SimpleOnGestureListener() {



            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.i("DEBUG", e1 + "-" + e2);

                float DeltaX = e2.getX() - e1.getX();
                float DeltaY = e2.getY() - e1.getY();


                if (Math.abs(DeltaX) > DElTA_MIN) {
                    if (DeltaX < 0) {
                        //context.onSwipe(SwipeDirection.RIGHT_TO_LEFT);
                        return true;
                    } else {
                       // context.onSwipe(SwipeDirection.LEFT_TO_RIGHT);
                        return true;
                    }
                } else {
                    if (Math.abs(DeltaY) > DElTA_MIN) {
                        if (DeltaY < 0) {
                           // context.onSwipe(SwipeDirection.BOTTOM_TO_TOP);
                            return true;
                        } else {
                          //  context.onSwipe(SwipeDirection.TOP_TO_BOTTOM);
                            return true;


                        }
                    }
                }


                return false;
            }

        })
        ;
    }

    public static enum SwipeDirection {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT, TOP_TO_BOTTOM, BOTTOM_TO_TOP
    }


}



