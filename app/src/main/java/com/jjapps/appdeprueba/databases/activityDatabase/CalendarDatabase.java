package com.jjapps.appdeprueba.databases.activityDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jjapps.appdeprueba.models.DailyActivity;


@Database(entities = {DailyActivity.class}, version = 1)
public abstract class CalendarDatabase extends RoomDatabase {
    public abstract CalendarDao getCalendarDao();
}
