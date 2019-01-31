package com.example.android.moodtracker;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public final class MoodHistoryAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater inflater;
    private List<MoodData> listOfMoodData;
    private int screenWidth;


    public MoodHistoryAdapter(Context baseContext, List<MoodData> moodDataList) {

        context = baseContext;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        this.listOfMoodData = moodDataList;


    }

    @Nullable
    public Object getItem(int position) {
        return this.listOfMoodData.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getCount() {

        return this.listOfMoodData.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view;


        if (convertView == null) {
            view = inflater.inflate(R.layout.list_items_row, parent, false);
            TextView numberOfTheDay = view.findViewById(R.id.numberOfTheDay);
            ImageButton commentIcon = view.findViewById(R.id.commentIcon);
            TextView days_ago = view.findViewById(R.id.days_ago);

            daysAgo(position, days_ago);


            switch (this.listOfMoodData.get(position).MOOD) {


                case 0: //Smiley Sad
                    view.setBackgroundResource(R.color.color_sad);
                    numberOfTheDay.setWidth(screenWidth() / 7);
                    IconOrNot(position, commentIcon);
                    break;

                case 1: //Smiley Disappointed
                    view.setBackgroundResource(R.color.color_disappointed);
                    numberOfTheDay.setWidth(screenWidth() / 5);
                    IconOrNot(position, commentIcon);
                    break;

                case 2: //Smiley Normal
                    view.setBackgroundResource(R.color.color_normal);
                    numberOfTheDay.setWidth(screenWidth() / 3);
                    IconOrNot(position, commentIcon);
                    break;

                case 3: //Smiley Happy
                    view.setBackgroundResource(R.color.color_happy);
                    numberOfTheDay.setWidth(screenWidth() / 2);
                    IconOrNot(position, commentIcon);
                    break;

                case 4: //Smiley Super_Happy
                    view.setBackgroundResource(R.color.color_super_happy);
                    numberOfTheDay.setWidth(screenWidth() / 1);
                    IconOrNot(position, commentIcon);
                    break;

                default:
                    break;
            }
        } else {
            view = convertView;
        }

        return view;
    }


    private void daysAgo(int position, TextView days_ago) {

        Date t = new Date();
        Date d = new Date();

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String today = simpleDateFormat.format(new Date());

        String daysAgo = listOfMoodData.get(position).WHEN_;


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            d = sdf.parse(daysAgo);
            t = sdf.parse(today);

//            Log.i("todayT", " " + t);
//            Log.i("todayD", " " + d);


        } catch (ParseException ex) {

            Log.i("exception", "Hay un problemo!");

        }

        long diff = t.getTime() - d.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);

        int combien = (int) diffInDays;

        switch (combien) {

            case 0:
                days_ago.setText(R.string.aujourdhui);
                break;

            case 1:
                days_ago.setText(R.string.yesterday);
                break;

            case 2:
                days_ago.setText(R.string.two_days_ago);
                break;

            case 3:
                days_ago.setText(R.string.three_days_ago);
                break;

            case 4:
                days_ago.setText(R.string.four_days_ago);
                break;

            case 5:
                days_ago.setText(R.string.five_days_ago);
                break;

            case 6:
                days_ago.setText(R.string.six_days_ago);
                break;

            case 7:
                days_ago.setText(R.string.a_week_ago);
                break;

            default:
                String message = context.getString(R.string.il_y_a_plusieurs_jours, combien);
                days_ago.setText(message);
                break;

        }


        }

        private void IconOrNot ( int position, ImageButton commentIcon){

            String iconOrNot = listOfMoodData.get(position).COMMENT;

            if (iconOrNot != null) {
                commentIcon.setVisibility(View.VISIBLE);
            } else {
                commentIcon.setVisibility(View.INVISIBLE);
            }
        }

        private int screenWidth () {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);

                screenWidth = displayMetrics.widthPixels;
            }
            return screenWidth;
        }


    }
