package com.example.healthtracking.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
/*
* create database class
**/
@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao
}