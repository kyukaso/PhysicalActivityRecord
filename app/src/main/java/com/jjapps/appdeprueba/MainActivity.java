package com.jjapps.appdeprueba;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjapps.appdeprueba.databases.activityDatabase.DataManager;
import com.jjapps.appdeprueba.models.DailyActivity;
import com.jjapps.appdeprueba.ui.CalendarAdapter;
import com.jjapps.appdeprueba.ui.popups.ActivityPopup;
import com.jjapps.appdeprueba.ui.popups.WeightPopup;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private CalendarAdapter calendarAdapter;

    private DataManager db;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DataManager.get(this);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        if (selectedDate.getDayOfWeek().getValue() == 5){
            weightDialogPopup();
        }
    }

    // Gets all items related to the functionality of the application
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void weightDialogPopup(){
        WeightPopup weightPopup = new WeightPopup(this);
        weightPopup.show();

        weightPopup.getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightPopup.onClick();
                setMonthView();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void activityDialogPopup(DailyActivity activity){
        if (activity.activity != -1) {
            ActivityPopup activityPopup = new ActivityPopup(this, activity);
            activityPopup.show();
            activityPopup.getActivitySubmit().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activityPopup.onClick();
                    setMonthView();
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    // Sets the calendar view in function of the current selected date
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<DailyActivity> daysInMonth = daysInMonthArray(selectedDate);

        calendarAdapter = new CalendarAdapter(daysInMonth, this, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    // Gets the days on the requested month
    private ArrayList<DailyActivity> daysInMonthArray(LocalDate date) {
        ArrayList<DailyActivity> daysInMonthArray = new ArrayList<>();
        // First: Get all days on current date
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++){
            // Second: Check if position corresponds to month
            if(i < dayOfWeek || i >= daysInMonth + dayOfWeek){
                daysInMonthArray.add(new DailyActivity(0, -1, 0, 0.0f, 0.0f));
            }
            else{
                // Third: Check if date is already on Database
                DailyActivity activity;
                if ((activity = db.getActivity(yearMonth.atDay(i - dayOfWeek + 1).toEpochDay())) != null){
                    daysInMonthArray.add(activity);
                }
                // Fourth: If not, create new daily activity object
                else {
                    daysInMonthArray.add(new DailyActivity(yearMonth.atDay(i - dayOfWeek + 1).toEpochDay(),
                            0, 0, 0.0f, 0.0f));
                }
            }
        }

        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    // Gives format to the text to be set on monthYearTV in relation to the given date
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position) {
        this.activityDialogPopup(calendarAdapter.getActivity(position));
    }
}