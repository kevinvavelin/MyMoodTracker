package com.example.android.moodtracker;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public final class MoodHistoryAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater inflater;
    //   private    ArrayList<MoodData> moodDataList = ArrayList<>;
   // private ArrayList moodDataList = new ArrayList();
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
            // switch (moodDataList.get(position)) {
            switch (this.listOfMoodData.get(position).MOOD) {
                //   case 0: //Smiley Sad
//            switch (position) {

                case 0: //Smiley Sad
                    view.setBackgroundResource(R.color.color_sad);
                    numberOfTheDay.setWidth(screenWidth() / 5);
                    IconOrNot(position, commentIcon);
                 //   commentIcon.setVisibility(View.GONE);
                    // visible or gone comment icon
                    // on clickable comment icon to open Toast with the message stored in the Database
                    // Appear text "One week ago", "6 days ago", etc...

                    break;

                case 1: //Smiley Disappointed
                    view.setBackgroundResource(R.color.color_disappointed);
                    numberOfTheDay.setWidth(screenWidth() / 4);
                    IconOrNot(position, commentIcon);
                  //  commentIcon.setVisibility(View.GONE);
                    break;

                case 2: //Smiley Normal
                    view.setBackgroundResource(R.color.color_normal);
                    numberOfTheDay.setWidth(screenWidth() / 3);
                    IconOrNot(position, commentIcon);
                   // commentIcon.setVisibility(View.GONE);
                    break;

                case 3: //Smiley Happy
                    view.setBackgroundResource(R.color.color_happy);
                    numberOfTheDay.setWidth(screenWidth() / 2);
                    IconOrNot(position, commentIcon);
                   // commentIcon.setVisibility(View.GONE);
                    break;

                case 4: //Smiley Super_Happy
                    view.setBackgroundResource(R.color.color_super_happy);
                    numberOfTheDay.setWidth(screenWidth() /  1);
                    IconOrNot(position, commentIcon);
                  //  commentIcon.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;
            }
        } else {
            view = convertView;
        }

        return view;
    }

    private void IconOrNot(int position, ImageButton commentIcon) {

       String iconOrNot = listOfMoodData.get(position).COMMENT;

       if ( iconOrNot != null ){
           commentIcon.setVisibility(View.VISIBLE);
       }
       else {
           commentIcon.setVisibility(View.GONE);
       }
    }

    private int screenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            screenWidth = displayMetrics.widthPixels;
        }
        return screenWidth;
    }


}
