package com.jjapps.appdeprueba.models;


import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.time.LocalDate;

/*
    Daily activity codes:
   -1: Not in month
    0: No data
    1: Running
 */

@Entity(tableName = "activity")
public class DailyActivity {
    @PrimaryKey
    public long day;
    @ColumnInfo(name = "activity")
    public int activity;
    @ColumnInfo(name = "calories")
    public int calories;
    @ColumnInfo(name = "kilometres")
    public float kilometres;
    @ColumnInfo(name = "time")
    public float time;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DailyActivity(long day, int activity, int calories, float kilometres, float time) {
        this.day = day;
        this.activity = activity;
        this.calories = calories;
        this.kilometres = kilometres;
        this.time = time;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LocalDate getDay() {
        return LocalDate.ofEpochDay(day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDay(LocalDate day) {
        this.day = day.toEpochDay();
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getKilometres() {
        return kilometres;
    }

    public void setKilometres(float kilometres) {
        this.kilometres = kilometres;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

}
