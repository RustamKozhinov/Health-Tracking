package com.example.healthtracking.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import com.example.healthtracking.repositories.MainRepository

class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel(){

    //implementation of statistics

    // total time run
    val totalTimeRun = mainRepository.getTotalTimeInMillis()

    //total distance
    val totalDistance = mainRepository.getTotalDistance()

    //total calories burned
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()

    //total average speed
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()

    //runs sorted by date
    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
}