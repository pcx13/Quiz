package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentCapitalsBinding

class CapitalsFragment:Fragment(R.layout.fragment_capitals) {

    private lateinit var binding:FragmentCapitalsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentCapitalsBinding.bind(view)
    }
}