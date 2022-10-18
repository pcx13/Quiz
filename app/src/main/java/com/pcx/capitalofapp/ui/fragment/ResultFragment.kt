package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.databinding.FragmentResultBinding
import com.pcx.capitalofapp.ui.viewmodel.ResultViewModel

class ResultFragment : Fragment(R.layout.fragment_result) {

    private lateinit var binding: FragmentResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)
        binding.resultFragment = this

        val bundle: ResultFragmentArgs by navArgs()

        binding.trueFF = bundle.trueF
        val t=binding.trueFF!!

        binding.resultText = "Success rate ${t * 10}%"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ResultViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonTryAgain() {
        findNavController().navigate(R.id.fromResult_toMain)
    }
}