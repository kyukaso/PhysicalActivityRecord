package com.jjapps.appdeprueba.databases.activityDatabase;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.jjapps.appdeprueba.models.DailyActivity;

import java.util.List;

public class DataManager{

    @SuppressLint("StaticFieldLeak")
    private static DataManager sDataManager;
    private CalendarDao mCalendarDao;

    private DataManager(Context context){
        Context appContext = context.getApplicationContext();
        CalendarDatabase database = Room.databaseBuilder(appContext, CalendarDatabase.class, "activity").allowMainThreadQueries().build();
        mCalendarDao = database.getCalendarDao();
    }

    public static DataManager get(Context context){
        if (sDataManager == null){
            sDataManager = new DataManager(context);
        }
        return sDataManager;
    }

    // Methods related to Activity DB
    public List<DailyActivity> getAllActivities(){
        return mCalendarDao.getAll();
    }

    public DailyActivity getActivity(long day){
        return mCalendarDao.findByName(day);
    }

    public void insertActivity(DailyActivity activity){
        if (getActivity(activity.day) == null){
            mCalendarDao.insert(activity);
        }
        else {
            updateActivity(activity);
        }
    }

    public void updateActivity(DailyActivity activity){
        mCalendarDao.update(activity);
    }

    public void deleteActivity(DailyActivity activity){
        mCalendarDao.delete(activity);
    }
}
