package com.example.weather.model

data class WeatherList (
    val main: Main,
    val weather: ArrayList<WeatherCloud>,
    val dt_txt: String
)