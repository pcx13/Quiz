package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentResultBinding

class ResultFragment:Fragment(R.layout.fragment_result) {

    private lateinit var binding:FragmentResultBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentResultBinding.bind(view)

        binding.buttonTryAgain.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fromResult_toMain)
        }
    }
}