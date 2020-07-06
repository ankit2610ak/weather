package com.example.weather.ui

import ApiClient
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weather.model.Weather
import com.example.weather.model.WeatherList
import com.example.weather.room.WeatherDatabase
import retrofit2.Call
import retrofit2.Response
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Weather5DaysViewModel(application: Application) : AndroidViewModel(application) {
    var weatherDatabase: WeatherDatabase
    private val sharedPrefFile = "sharedpreference"
    val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )

    init {
        weatherDatabase = WeatherDatabase.getInstance(application.applicationContext)
    }

    fun getWeatherDetails(lattitude: Float, longitude: Float, appId: String) {
        val call: Call<Weather> =
            ApiClient.getClient.getWeatherForecast(lattitude, longitude, appId)
        call.enqueue(object : retrofit2.Callback<Weather> {
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("Weather5DaysViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<Weather>,
                response: Response<Weather>
            ) {
//                liveData.postValue(response.body())
                var arrayList = response.body()?.list
                if (arrayList != null) {
                    for (list in arrayList) {
                        list.lat = lattitude
                        list.lon = longitude
                    }
                }
                val timeStamp = System.currentTimeMillis()
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                val date = Date(timeStamp)

                val editor = sharedPreferences.edit()
                editor.putString("time", formatter.format(date))
                editor.apply()
                insertDataIntoDB(arrayList!!)
            }

        })
    }

    private fun insertDataIntoDB(arrayList: ArrayList<WeatherList>) {
        Thread(Runnable {
            weatherDatabase.sleepDatabaseDao.insertAll(arrayList)
        }).start()

    }

    fun getLiveDataFromDatabase(
    ): LiveData<List<WeatherList>> {
        return weatherDatabase.sleepDatabaseDao.getAll()
    }

}