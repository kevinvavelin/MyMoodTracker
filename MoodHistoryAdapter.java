package com.example.android.moodtracker;


import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public final class MoodHistoryAdapter extends BaseAdapter {


    private   Context context ;
    private   LayoutInflater inflater;
    //   private    ArrayList<MoodData> moodDataList = ArrayList<>;
    private ArrayList moodDataList = new ArrayList();



    @Nullable
    public Object getItem(int position) {
        return this.moodDataList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getCount() {

        return this.moodDataList.size();
    }

    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_items_row, parent, false);
            TextView numberOfTheDay = view.findViewById(R.id.textOfTheDay);
         // switch (moodDataList.get(position)) {
            switch (position) {

                case 0: //Smiley Sad
                    view.setBackgroundResource(R.color.color_sad);
                    numberOfTheDay.setText("Sad");
                    //display width
                    // visible or gone comment icon
                    // on clickable comment icon to open Toast with the message stored in the Database

                    break;

                case 1: //Smiley Disappointed
                    view.setBackgroundResource(R.color.color_disappointed);
                    numberOfTheDay.setText("Disappointed");
                    break;

                case 2: //Smiley Normal
                    view.setBackgroundResource(R.color.color_normal);
                    numberOfTheDay.setText("Normal");
                    break;

                case 3: //Smiley Happy
                    view.setBackgroundResource(R.color.color_happy);
                    numberOfTheDay.setText("Happy");
                    break;

                case 4: //Smiley Super_Happy
                    view.setBackgroundResource(R.color.color_super_happy);
                    numberOfTheDay.setText("Super_Happy");
                    break;

            }} else {
                view = convertView;
            }

            return view;
        }



    public MoodHistoryAdapter(Context baseContext, List<MoodData> moodDataList) {

        context = baseContext;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


    }



    }
