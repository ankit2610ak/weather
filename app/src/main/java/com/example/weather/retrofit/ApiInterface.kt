package com.example.weather.retrofit

import com.example.weather.model.Weather
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    companion object {
    }

    @POST("forecast")
    fun getWeatherForecast(
        @Query("lat") lat: Float,
        @Query("lon") name: Float,
        @Query("appid") appid: String

    ): Call<Weather>

}