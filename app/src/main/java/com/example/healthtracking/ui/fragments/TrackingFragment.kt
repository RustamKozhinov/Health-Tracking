package com.example.healthtracking.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.healthtracking.services.TrackingService
import com.example.healthtracking.R
import com.example.healthtracking.viewmodels.MainViewModel
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint
import com.example.healthtracking.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.example.healthtracking.other.Constants.POLYLINE_COLOR
import com.example.healthtracking.other.Constants.POLYLINE_WIDTH
import com.example.healthtracking.other.Constants.MAP_ZOOM
import com.example.healthtracking.other.Constants.ACTION_PAUSE_SERVICE
import com.google.android.gms.maps.CameraUpdateFactory
import com.example.healthtracking.services.Polyline


import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.fragment_tracking.*


/*
* the Map's
* */
@AndroidEntryPoint
class TrackingFragment : Fragment() {

    //implement view model class
    private val viewModel: MainViewModel by viewModels()

    //drawing the running track on the map
    private var isTracking = false
    private var pathPoints = mutableListOf<Polyline>()

    //variable map
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //button a timer
        btnToggleRun.setOnClickListener {
            toggleRun()
        }

        //the map's lifecycle
        mapView.onCreate(savedInstanceState)

        //setup map
        mapView.getMapAsync {
            map = it
            addAllPolylines()
        }
        subscribeToObservers()

        return inflater.inflate(R.layout.fragment_tracking, container, false)
    }

    /*
    * drawing the running track on the map
    * subscribe to observers
    */
    private fun subscribeToObservers() {
        TrackingService.isTracking.observe(viewLifecycleOwner, Observer {
            updateTracking(it)
        })

        TrackingService.pathPoints.observe(viewLifecycleOwner, Observer {
            pathPoints = it
            addLatestPolyline()
            moveCameraToUser()
        })
    }

    /*
     * drawing the running track on the map
     * toggle run
    */
    private fun toggleRun() {
        if(isTracking) {
            sendCommandToService(ACTION_PAUSE_SERVICE)
        } else {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
    }

    /*
     * drawing the running track on the map
     * update tracking
    */
    private fun updateTracking(isTracking: Boolean) {
        this.isTracking = isTracking
        if(!isTracking) {
            btnToggleRun.text = "Start"
            btnFinishRun.visibility = View.VISIBLE
        } else {
            btnToggleRun.text = "Stop"
            btnFinishRun.visibility = View.GONE
        }
    }

    /*
     *drawing the running track on the map
     *move camera to user
    */
    private fun moveCameraToUser() {
        if(pathPoints.isNotEmpty() && pathPoints.last().isNotEmpty()) {
            map?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    pathPoints.last().last(),
                    MAP_ZOOM
                )
            )
        }
    }

    /*
     *drawing the running track on the map
     *add all polyline
    */
    private fun addAllPolylines() {
        for(polyline in pathPoints) {
            val polylineOptions = PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .addAll(polyline)
            map?.addPolyline(polylineOptions)
        }
    }

    /*
     *drawing the running track on the map
     *drawing last polyline
    */
    private fun addLatestPolyline() {
        if(pathPoints.isNotEmpty() && pathPoints.last().size > 1) {
            val preLastLatLng = pathPoints.last()[pathPoints.last().size - 2]
            val lastLatLng = pathPoints.last().last()
            val polylineOptions = PolylineOptions()
                .color(POLYLINE_COLOR)
                .width(POLYLINE_WIDTH)
                .add(preLastLatLng)
                .add(lastLatLng)
            map?.addPolyline(polylineOptions)
        }
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


