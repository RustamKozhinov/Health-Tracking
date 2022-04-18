package com.example.healthtracking.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.healthtracking.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.healthtracking.db.RunDao

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var runDao: RunDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}