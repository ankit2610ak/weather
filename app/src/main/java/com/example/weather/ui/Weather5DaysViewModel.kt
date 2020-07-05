package com.example.weather.ui

import ApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.Weather
import com.example.weather.model.WeatherList
import retrofit2.Call
import retrofit2.Response
import java.math.BigDecimal

class Weather5DaysViewModel : ViewModel() {
    private var liveData: MutableLiveData<Weather> = MutableLiveData()
    val _livedata: LiveData<Weather>
        get() = liveData

    fun getWeatherDetails(lat: Float, lon: Float, appId: String) {
        val call: Call<Weather> = ApiClient.getClient.getWeatherForecast(lat, lon, appId)
        call.enqueue(object : retrofit2.Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("RepoActivityViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<Weather>,
                response: Response<Weather>
            ) {
                liveData.postValue(response.body())
            }

        })
    }


}