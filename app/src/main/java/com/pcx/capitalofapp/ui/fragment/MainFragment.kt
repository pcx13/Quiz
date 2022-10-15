package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentMainBinding

class MainFragment:Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMainBinding.bind(view)
        binding.mainFragment=this
    }

    fun buttonFlags(){
        view?.let { Navigation.findNavController(it).navigate(R.id.fromMain_toFlags) }
    }

    fun buttonCapitals(){
        view?.let { Navigation.findNavController(it).navigate(R.id.fromMain_toCapitals) }
    }
}