package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    lateinit var adapter: WeatherAdapter
    private var bannerImageList: ArrayList<WeatherList> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.weather5_days_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(Weather5DaysViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_weather)
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            bannerImageList.clear()
            bannerImageList.addAll(it.list)
            adapter.notifyDataSetChanged()
        })
        adapter = WeatherAdapter(bannerImageList, this.requireContext())

        viewModel.getWeatherDetails(
            arguments?.getFloat("lat")!!,
            arguments?.getFloat("lat")!!,
            "b426a7540d88be5d89c501c685cee1e7"
        )
        recyclerView.adapter = adapter

        return view
    }


}