package com.example.healthtracking.other

import android.graphics.Color

object Constants {

    //database constant
    const val RUNNING_DATABASE_NAME = "running_db"

    //location permission constant
    const val REQUEST_CODE_LOCATION_PERMISSION = 0

    //implented the stop watch
    const val TIMER_UPDATE_INTERVAL = 50L

    //tracking service constants
    const val ACTION_START_OR_RESUME_SERVICE = "ACTION_START_OR_RESUME_SERVICE"
    const val ACTION_PAUSE_SERVICE = "ACTION_PAUSE_SERVICE"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

    //for foreground service
    const val ACTION_SHOW_TRACKING_FRAGMENT = "ACTION_SHOW_TRACKING_FRAGMENT"
    const val NOTIFICATION_CHANNEL_ID = "tracking_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Tracking"
    const val NOTIFICATION_ID = 1

    //for tracking user location in the background
    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L

    //for drawing the running track on the map
    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f
    const val MAP_ZOOM = 15f
}