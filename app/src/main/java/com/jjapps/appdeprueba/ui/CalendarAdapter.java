package com.jjapps.appdeprueba.ui;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.jjapps.appdeprueba.databases.weightDatabase.WeightManager;
import com.jjapps.appdeprueba.models.DailyActivity;
import com.jjapps.appdeprueba.models.WeightData;
import com.jjapps.appdeprueba.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{

    private final ArrayList<DailyActivity> daysOfMonth;
    private final OnItemListener onItemListener;
    private final WeightManager weightManager;

    public CalendarAdapter(ArrayList<DailyActivity> daysOfMonth, OnItemListener onItemListener, Context context) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.weightManager = WeightManager.get(context);
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        DailyActivity activity = daysOfMonth.get(position);
        // Not in month
        if (activity.activity == -1){
            setHolderValues(holder, "", -1, -1.0f);
            holder.itemView.setBackgroundResource(R.drawable.out_of_month_box);
        }
        // In month
        else {
            LocalDate day = LocalDate.ofEpochDay(daysOfMonth.get(position).day);
            // Box colors
                // position + 1: Sunday, position + 2: Saturday
            if ((position + 1) % 7 == 0 || (position + 2) % 7 == 0) {
                holder.itemView.setBackgroundResource(R.drawable.box_border_weekend);
                // Friday
            } else if ((position + 3) % 7 == 0) {
                holder.itemView.setBackgroundResource(R.drawable.box_border_friday);
                WeightData weight = weightManager.getWeight(activity.day);
                    // Set Weight Data
                if (weight != null){
                    holder.weight.setText(String.format("%s KG", weight.getWeight()));
                }
            }
            // Data
                // No data
            if (daysOfMonth.get(position).activity == 0){
                setHolderValues(holder, String.valueOf(day.getDayOfMonth()), 0, 0.0f);
            }
                // Data
            else{
                setHolderValues(holder, String.valueOf(day.getDayOfMonth()), activity.calories, activity.kilometres);
            }
        }
    }

    private void setHolderValues(@NonNull CalendarViewHolder holder, String day, int calories, float kilometres) {
        holder.dayOfMonth.setText(day);
        // Calories
        if (calories == -1) {
            holder.calories.setText("");
        }
        else{
            holder.calories.setText(String.format("%d Cal", calories));
        }
        // Kilometres
        if (kilometres == -1.0f) {
            holder.kilometres.setText("");
        }
        else{
            holder.kilometres.setText(String.format("%s KM", kilometres));
        }
    }

    @Override
    public int getItemCount() {return daysOfMonth.size();}

    public interface OnItemListener{
        void onItemClick(int position);
    }

    public DailyActivity getActivity(int position){
        return daysOfMonth.get(position);
    }
}
