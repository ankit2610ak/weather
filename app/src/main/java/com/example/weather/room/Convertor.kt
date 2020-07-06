package com.example.weather.room

import androidx.room.TypeConverter
import com.example.weather.model.Main
import com.example.weather.model.WeatherCloud
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Convertor {
    val gson = Gson()

    @TypeConverter
    fun strFromMain(main: Main): String {
        return gson.toJson(main)
    }

    @TypeConverter
    fun strToMain(str: String): Main {
        return gson.fromJson(str, Main::class.java)
    }

    @TypeConverter
    fun strFromArrayListOfWeatherCloud(arr: ArrayList<WeatherCloud>): String {
        return gson.toJson(arr)
    }

    @TypeConverter
    fun strToArrayListOfWeatherCloud(str: String): ArrayList<WeatherCloud> {
        val listType: Type = object : TypeToken<ArrayList<WeatherCloud?>?>() {}.type
        return gson.fromJson(str, listType)
    }
}