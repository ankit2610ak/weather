package com.example.weather.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapter.WeatherAdapter
import com.example.weather.model.WeatherList

class Weather5DaysFragment : Fragment() {

    companion object {
        fun newInstance() = Weather5DaysFragment()
    }

    private lateinit var viewModel: Weather5DaysViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var timeText: TextView
    lateinit var adapter: WeatherAdapter
    private var bannerImageList: ArrayList<WeatherList> = ArrayList()
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather5_days_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(Weather5DaysViewModel::class.java)
        sharedPreferences = this.requireContext().getSharedPreferences("sharedpreference",Context.MODE_PRIVATE)
        recyclerView = view.findViewById(R.id.recycler_view_weather)
        timeText = view.findViewById(R.id.time)
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        viewModel.getLiveDataFromDatabase(
        ).observe(viewLifecycleOwner, Observer {
            var arrayList = ArrayList<WeatherList>()
            for (item in it){
                if (item.lat == arguments?.getFloat("lat") && item.lon == arguments?.getFloat("lon")){
                    arrayList.add(item)
                }
            }
            if (arrayList.isNotEmpty()){
                timeText.text = "Last updated: " +sharedPreferences.getString("time", " ")

            }else{
                timeText.text = "No Data Found"
            }
            bannerImageList.clear()
            bannerImageList.addAll(arrayList)
            adapter.notifyDataSetChanged()
        })

        adapter = WeatherAdapter(bannerImageList, this.requireContext())

        viewModel.getWeatherDetails(
            arguments?.getFloat("lat")!!,
            arguments?.getFloat("lon")!!,
            "b426a7540d88be5d89c501c685cee1e7"
        )
        recyclerView.adapter = adapter

        return view
    }


}