package com.example.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.WeatherList

class WeatherAdapter(
    private val weatherForecastList: ArrayList<WeatherList>
    , private val context: Context

) : RecyclerView.Adapter<WeatherAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(

            LayoutInflater.from(context).inflate(R.layout.list_item_weather, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return weatherForecastList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val weatherDetails = weatherForecastList[position]
        holder.date.text = weatherDetails.dt_txt
        holder.temp.text = weatherDetails.main.temp.toString() + " K"
        holder.weatherInfo.text = weatherDetails.weather[0].description

    }

    class CustomViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var date: TextView = itemLayoutView.findViewById(R.id.date)
        var temp: TextView = itemLayoutView.findViewById(R.id.temp)
        var weatherInfo: TextView = itemLayoutView.findViewById(R.id.weather)
    }

}
