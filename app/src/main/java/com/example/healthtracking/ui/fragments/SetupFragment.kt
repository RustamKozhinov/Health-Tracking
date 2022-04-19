package com.example.healthtracking.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthtracking.databinding.FragmentSetupBinding
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.healthtracking.R


class SetupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentSetupBinding  =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_setup, container, false
            )

        binding.tvContinue.setOnClickListener {
            findNavController().navigate(R.id.action_setupFragment_to_runFragment)
        }


        return binding.root
    }

}