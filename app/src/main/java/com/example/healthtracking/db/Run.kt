package com.example.healthtracking.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class Run(
    var img: Bitmap? = null,//image
    var timestamp: Long = 0L,//times tamp
    var avgSpeedInKMH: Float = 0f,//speed in km/h
    var distanceInMeters: Int = 0,//distance in meters
    var timeInMillis: Long = 0,//time in millis
    var caloriesBurned: Int = 0//calories
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null//id
}