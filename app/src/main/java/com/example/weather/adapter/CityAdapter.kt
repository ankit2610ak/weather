package com.example.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.JSONDataClass

class CityAdapter(
    private var cityArrayList: ArrayList<JSONDataClass>
    , private val context: Context

) : RecyclerView.Adapter<CityAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(

            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )

    }

    override fun getItemCount(): Int = cityArrayList.size

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val cityItem = cityArrayList[position]

        holder.name.text = cityItem.city.name
        holder.country.text = cityItem.city.country

    }


    class CustomViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var name: TextView = itemLayoutView.findViewById(R.id.name)
        var country: TextView = itemLayoutView.findViewById(R.id.country)

    }

}
