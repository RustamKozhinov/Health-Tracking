package com.example.healthtracking.repositories

import javax.inject.Inject
import com.example.healthtracking.db.Run
import com.example.healthtracking.db.RunDao

/*
* class repository get methods from db
*/
class MainRepository @Inject constructor(
    val runDao: RunDao
) {
    //insert count
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    //delete count
    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    //get all runs sorted by date
    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    //get all runs sorted by distance
    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    //get all runs sorted by time in mills
    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeInMillis()

    //get all runs sorted by avg speed
    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    //get all runs sorted by calories burned
    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    //get total avg speed
    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    //get total distance
    fun getTotalDistance() = runDao.getTotalDistance()

    //get total calories burned
    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    //get total time in millis
    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()
}