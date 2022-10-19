package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentMainBinding
import com.pcx.capitalofapp.ui.viewmodel.MainViewModel

class MainFragment:Fragment(R.layout.fragment_main) {

    private lateinit var binding:FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentMainBinding.bind(view)
        binding.mainFragment=this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MainViewModel by viewModels()
        viewModel=tempViewModel
    }

    fun buttonFlags(){
        findNavController().navigate(R.id.fromMain_toFlags)
    }

    fun buttonCapitals(){
        findNavController().navigate(R.id.fromMain_toCapitals)
    }
}