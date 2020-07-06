package com.example.weather.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.JSONDataClass
import com.example.weather.ui.weather.search.SearchFragment

class CityAdapter(
    private var cityArrayList: ArrayList<JSONDataClass>
    , private val context: Context,
    val fromSearchFragment : Boolean

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
       /* val bundle = Bundle()
        bundle.putFloat("lat", cityItem.city.coord.lat)
        bundle.putFloat("lon", cityItem.city.coord.lon)
*/
        val bundle = bundleOf("lat" to cityItem.city.coord.lat, "lon" to cityItem.city.coord.lon)
        if(fromSearchFragment){
            holder.itemView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_searchFragment_to_weather5DaysFragment, bundle)
                Log.d("TAG", "lat: $bundle.")
            }
        }

    }

    fun updateList(arrayList: ArrayList<JSONDataClass>?) {
            cityArrayList.clear()
            cityArrayList.addAll(arrayList!!)
            notifyDataSetChanged()
    }


    class CustomViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var name: TextView = itemLayoutView.findViewById(R.id.name)
        var country: TextView = itemLayoutView.findViewById(R.id.country)

    }


}
