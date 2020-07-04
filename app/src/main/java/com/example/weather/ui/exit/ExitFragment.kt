package com.example.weather.ui.exit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.weather.R
import kotlin.system.exitProcess

class ExitFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(this.requireContext(),"Application Closed", Toast.LENGTH_SHORT ).show()
        exitProcess(1)
    }
}