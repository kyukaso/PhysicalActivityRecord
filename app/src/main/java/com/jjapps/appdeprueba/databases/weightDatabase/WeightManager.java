package com.jjapps.appdeprueba.databases.weightDatabase;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Room;

import com.jjapps.appdeprueba.models.WeightData;

import java.util.List;

public class WeightManager {

    @SuppressLint("StaticFieldLeak")
    private static WeightManager sWeightManager;
    private WeightDao mWeightData;

    private WeightManager(Context context){
        Context appContext = context.getApplicationContext();
        WeightDatabase database = Room.databaseBuilder(appContext, WeightDatabase.class, "weight").allowMainThreadQueries().build();
        mWeightData = database.getWeightDao();
    }

    public static WeightManager get(Context context){
        if (sWeightManager == null){
            sWeightManager = new WeightManager(context);
        }
        return sWeightManager;
    }

    // Methods related to DB
    public List<WeightData> getAllWeights(){
        return mWeightData.getAll();
    }

    public WeightData getWeight(long day){
        return mWeightData.findByName(day);
    }

    public void insertWeight(WeightData weight){
        if (getWeight(weight.day) == null) {
            mWeightData.insert(weight);
        }
        else{
            updateWeight(weight);
        }
    }

    public void updateWeight(WeightData weight){
        mWeightData.update(weight);
    }

    public void deleteActivity(WeightData weight){
        mWeightData.delete(weight);
    }
}
