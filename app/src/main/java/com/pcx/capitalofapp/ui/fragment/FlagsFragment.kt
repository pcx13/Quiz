package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentFlagsBinding
import com.pcx.capitalofapp.ui.viewmodel.FlagsViewModel

class FlagsFragment:Fragment(R.layout.fragment_flags) {

    private lateinit var binding:FragmentFlagsBinding
    private lateinit var flagsViewModel: FlagsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentFlagsBinding.bind(view)

        flagsViewModel= FlagsViewModel()
        binding.flagsViewModel=flagsViewModel
    }
}