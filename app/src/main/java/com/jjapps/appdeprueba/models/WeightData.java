package com.jjapps.appdeprueba.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weight")
public class WeightData {
    @PrimaryKey
    public long day;
    @ColumnInfo(name = "weight")
    public float weight;

    public WeightData(long day, float weight) {
        this.day = day;
        this.weight = weight;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
