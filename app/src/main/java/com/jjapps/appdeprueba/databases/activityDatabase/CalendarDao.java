package com.jjapps.appdeprueba.databases.activityDatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jjapps.appdeprueba.models.DailyActivity;

import java.util.List;

@Dao
public interface CalendarDao {
    @Query("SELECT * FROM activity")
    List<DailyActivity> getAll();

    @Query("SELECT * FROM activity WHERE day LIKE :day")
    DailyActivity findByName(long day);

    @Insert
    void insert(DailyActivity activities);

    @Delete
    void delete(DailyActivity activity);

    @Update
    void update(DailyActivity activity);
}
