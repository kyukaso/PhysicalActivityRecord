package com.jjapps.appdeprueba.databases.weightDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jjapps.appdeprueba.models.WeightData;

import java.util.List;

@Dao
public interface WeightDao {
    @Query("SELECT * FROM weight")
    List<WeightData> getAll();

    @Query("SELECT * FROM weight WHERE day LIKE :day")
    WeightData findByName(long day);

    @Insert
    void insert(WeightData weight);

    @Delete
    void delete(WeightData weight);

    @Update
    void update(WeightData weight);
}
