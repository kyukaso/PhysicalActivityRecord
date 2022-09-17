package com.jjapps.appdeprueba.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjapps.appdeprueba.R;
import com.jjapps.appdeprueba.models.DailyActivity;
import com.jjapps.appdeprueba.ui.CalendarAdapter;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView dayOfMonth;
    public final TextView calories;
    public final TextView kilometres;
    public final TextView weight;


    public final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        calories = itemView.findViewById(R.id.caloriesText);
        kilometres = itemView.findViewById(R.id.kilometresText);
        weight = itemView.findViewById(R.id.weightText);
        weight.setText("");
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getBindingAdapterPosition());
    }
}
