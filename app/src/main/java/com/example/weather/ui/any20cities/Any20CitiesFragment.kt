package com.example.weather.ui.any20cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapter.CityAdapter
import com.example.weather.model.JSONDataClass

class Any20CitiesFragment : Fragment() {

    private lateinit var model: Any20CitiesFragmentViewModel
    private lateinit var adapter: CityAdapter
    private var cityList: ArrayList<JSONDataClass> = ArrayList()
    private var recyclerView : RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        model = ViewModelProviders.of(this).get(Any20CitiesFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any20cities, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = GridLayoutManager(this.requireContext(), 3)
        model._livedata.observe(viewLifecycleOwner, Observer {
            cityList.clear()
            cityList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        model.setData()
        adapter = CityAdapter(cityList, this.requireContext())

        recyclerView?.adapter = adapter
        return root
    }
}