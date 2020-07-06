package com.example.weather.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weather.model.WeatherList

@Dao
interface WeatherDao {
    @Insert
    fun insert(weatherEntity: WeatherList)

    @Insert
    fun insertAll(list: ArrayList<WeatherList>)


    @Query("SELECT * from weather_table WHERE id = :key")
    fun get(key: Int): WeatherList?

    @Query("SELECT * FROM weather_table")
    fun getAll(): LiveData<List<WeatherList>>

}