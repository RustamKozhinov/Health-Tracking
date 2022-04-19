package com.example.healthtracking.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.healthtracking.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import androidx.navigation.ui.setupWithNavController
import com.example.healthtracking.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import com.example.healthtracking.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize data binding
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this,R.layout.activity_main)

        //jump to tracking fragment if needed
        navigateToTrackingFragmentIfNeeded(intent)

        //handle clicks on menu items
        setSupportActionBar(toolbar)
        binding.bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        //remove bottom navigation
        navHostFragment.findNavController()
            .addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                    else -> bottomNavigationView.visibility = View.GONE
                }
            }
    }

    //new intent
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    //jump to tracking fragment if needed
    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
}