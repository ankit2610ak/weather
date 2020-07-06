package com.example.weather.ui.weather

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.adapter.CityAdapter
import com.example.weather.model.JSONDataClass
import com.example.weather.ui.weather.search.SearchViewModel

class WeatherFragment : Fragment() {


    private lateinit var model: SearchViewModel
    private lateinit var adapter: CityAdapter
    private var cityList: ArrayList<JSONDataClass> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var searchEdittext: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        searchEdittext = view.findViewById(R.id.searchEdittext)
        recyclerView?.layoutManager = GridLayoutManager(this.requireContext(), 3)
        model._livedata.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
        model.setData()
        adapter = CityAdapter(cityList, this.requireContext(), true)

        recyclerView?.adapter = adapter
        searchEdittext?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                Log.d("MainFragment", s.toString())
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                model.filterText(s.toString())
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
        return view
    }
}