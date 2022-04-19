package com.example.healthtracking.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.healthtracking.services.TrackingService
import com.example.healthtracking.R
import com.example.healthtracking.viewmodels.MainViewModel
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint
import com.example.healthtracking.other.Constants.ACTION_START_OR_RESUME_SERVICE
import kotlinx.android.synthetic.main.fragment_tracking.*

/*
* the Map's Lifecycle
* */
@AndroidEntryPoint
class TrackingFragment : Fragment() {

    //implement view model class
    private val viewModel: MainViewModel by viewModels()

    //variable map
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //button a timer
        btnToggleRun.setOnClickListener {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }

        //the map's lifecycle
        mapView.onCreate(savedInstanceState)

        //setup map
        mapView.getMapAsync {
            map = it
        }

        return inflater.inflate(R.layout.fragment_tracking, container, false)
    }

    //when you press the button, the timer runs in the background
    private fun sendCommandToService(action: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    //the map's lifecycle
    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    //the map's lifecycle
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    //the map's lifecycle
    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    //the map's lifecycle
    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    //the map's lifecycle
    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

}