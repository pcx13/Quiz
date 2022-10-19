package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.data.DatabaseHelper
import com.pcx.capitalofapp.data.QuizDao
import com.pcx.capitalofapp.databinding.FragmentCapitalsBinding
import com.pcx.capitalofapp.ui.viewmodel.CapitalsViewModel

class CapitalsFragment : Fragment(R.layout.fragment_capitals) {

    private lateinit var binding: FragmentCapitalsBinding
    private lateinit var viewModel: CapitalsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCapitalsBinding.bind(view)
        binding.capitalsFragment = this

        viewModel.dbs = DatabaseHelper(requireActivity())
        viewModel.questions = QuizDao().getRandom10Flag(viewModel.dbs)

        binding.trueText = viewModel.outcomeT
        binding.falseText = viewModel.outcomeF

        getQuiz()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CapitalsViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun getQuiz() {
        viewModel.quizzes()

        binding.apply {
            buttonA1.text = viewModel.allOptions.elementAt(0).capital
            buttonB1.text = viewModel.allOptions.elementAt(1).capital
            buttonC1.text = viewModel.allOptions.elementAt(2).capital
            buttonD1.text = viewModel.allOptions.elementAt(3).capital

            countryText = viewModel.trueAnswer.name
            meterText = viewModel.counter

            ivFlag3.setImageResource(
                resources.getIdentifier(
                    viewModel.trueAnswer.flag,
                    "drawable",
                    requireActivity().packageName
                )
            )
        }
    }

    private fun quizControl() {
        viewModel.questionCounter++

        if (viewModel.questionCounter != 10) {
            getQuiz()
        } else {
            val action =
                CapitalsFragmentDirections.fromCapitalsToResult(trueF = viewModel.trueCounter)
            findNavController().navigate(action)
        }
    }

    private fun correctQuiz(button: Button) {
        viewModel.correction(button)

        binding.trueText = viewModel.outcomeT
        binding.falseText = viewModel.outcomeF
    }

    fun buttonA1() {
        correctQuiz(binding.buttonA1)
        quizControl()
    }

    fun buttonB1() {
        correctQuiz(binding.buttonB1)
        quizControl()
    }

    fun buttonC1() {
        correctQuiz(binding.buttonC1)
        quizControl()
    }

    fun buttonD1() {
        correctQuiz(binding.buttonD1)
        quizControl()
    }
}