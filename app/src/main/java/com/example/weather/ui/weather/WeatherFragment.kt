package com.example.weather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.weather.R

class WeatherFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel
    private var search: TextView? = null
    private var currentLocation: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weatherViewModel =
            ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weather, container, false)
        search = root.findViewById(R.id.search)
        currentLocation = root.findViewById(R.id.current)

        search?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_weather_to_searchFragment)
        }

        currentLocation?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_nav_weather_to_currentLocationFragment)

        }

        return root
    }

}