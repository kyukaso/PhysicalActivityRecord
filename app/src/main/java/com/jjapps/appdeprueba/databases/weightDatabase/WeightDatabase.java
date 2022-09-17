package com.jjapps.appdeprueba.databases.weightDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jjapps.appdeprueba.models.WeightData;

@Database(entities = {WeightData.class}, version = 1)
public abstract class WeightDatabase extends RoomDatabase {
    public abstract WeightDao getWeightDao();
}
