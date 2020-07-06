package com.example.weather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class WeatherList (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val main: Main,
    val weather: ArrayList<WeatherCloud>,
    val dt_txt: String,
    var lat: Float,
    var lon: Float


)