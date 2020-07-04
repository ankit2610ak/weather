package com.example.weather.ui.any20cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R

class Any20CitiesFragment : Fragment() {

    private lateinit var any20CitiesFragmentViewModel: Any20CitiesFragmentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        any20CitiesFragmentViewModel =
                ViewModelProviders.of(this).get(Any20CitiesFragmentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any20cities, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        any20CitiesFragmentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}