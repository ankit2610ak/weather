package com.example.weather.ui.any20cities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.model.JSONDataClass
import com.example.weather.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Any20CitiesFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private var liveData: MutableLiveData<ArrayList<JSONDataClass>> = MutableLiveData()
    val _livedata: LiveData<ArrayList<JSONDataClass>>
        get() = liveData

    private val jsonFileString =
        Utils.getJsonDataFromAsset(application.applicationContext, "city.list.json")

    val gson = Gson()
    val listCountyType = object : TypeToken<ArrayList<JSONDataClass>>() {}.type

    private val listOfJSonData: ArrayList<JSONDataClass> =
        gson.fromJson(jsonFileString, listCountyType)

    fun setData() {
        liveData.postValue(listOfJSonData)
    }

}