package com.example.weather.ui.weather.currentLocation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.MainActivity
import com.example.weather.R
import com.example.weather.adapter.WeatherAdapter
import com.example.weather.model.WeatherList
import com.example.weather.ui.Weather5DaysViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class CurrentLocationFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentLocationFragment()
    }

    private lateinit var viewModel: Weather5DaysViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: WeatherAdapter
    var bannerImageList: ArrayList<WeatherList> = ArrayList()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override
    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.current_location_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(Weather5DaysViewModel::class.java)
        configureRecyclerView(view)
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(activity as MainActivity)

        viewModel.getLiveDataFromDatabase(
        ).observe(viewLifecycleOwner, Observer {
            bannerImageList.clear()
            bannerImageList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = WeatherAdapter(bannerImageList, this.requireContext())
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        if (ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1000)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                viewModel.getWeatherDetails(
                    it.latitude.toFloat(), it.longitude.toFloat(),
                    "b426a7540d88be5d89c501c685cee1e7"
                )
            }
        }
        return view

    }

    private fun configureRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_weather_current)
        recyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

}