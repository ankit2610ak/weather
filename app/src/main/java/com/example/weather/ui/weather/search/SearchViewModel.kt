package com.example.weather.ui.weather.search

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.model.JSONDataClass
import com.example.weather.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private var originalList: ArrayList<JSONDataClass> = ArrayList()
    val filteredList: ArrayList<JSONDataClass> = ArrayList()
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
        originalList.clear()
        originalList.addAll(listOfJSonData)
        if(filteredList.size > 0){
            liveData.postValue(filteredList)
        }
        else{
            liveData.postValue(originalList)
        }
    }

    fun filterText(text: String) {
        findFilteredList(text)
    }

    @SuppressLint("DefaultLocale")
    private fun findFilteredList(text: String) {
        filteredList.clear()
        for (item in originalList) {
            if (item.city.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        liveData.postValue(filteredList)
    }


}