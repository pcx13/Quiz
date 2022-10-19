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
import com.pcx.capitalofapp.databinding.FragmentFlagsBinding
import com.pcx.capitalofapp.ui.viewmodel.FlagsViewModel

class FlagsFragment : Fragment(R.layout.fragment_flags) {

    private lateinit var binding: FragmentFlagsBinding
    private lateinit var viewModel: FlagsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlagsBinding.bind(view)
        binding.flagsFragment = this

        viewModel.dbs = DatabaseHelper(requireActivity())
        viewModel.questions = QuizDao().getRandom10Flag(viewModel.dbs)

        binding.trueText = viewModel.outcomeT
        binding.falseText = viewModel.outcomeF

        getQuiz()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FlagsViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun getQuiz() {

        viewModel.quizzes()

        binding.apply {
            buttonA.text = viewModel.allOptions.elementAt(0).name
            buttonB.text = viewModel.allOptions.elementAt(1).name
            buttonC.text = viewModel.allOptions.elementAt(2).name
            buttonD.text = viewModel.allOptions.elementAt(3).name

            meterText = viewModel.counter

            ivFlag.setImageResource(
                resources.getIdentifier(
                    viewModel.trueAnswer.flag, "drawable",
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
            val action = FlagsFragmentDirections.fromFlagsToResult(trueF = viewModel.trueCounter)
            findNavController().navigate(action)
        }
    }

    private fun correctQuiz(button: Button) {
        viewModel.correction(button)

        binding.trueText = viewModel.outcomeT
        binding.falseText = viewModel.outcomeF
    }

    fun buttonA() {
        correctQuiz(binding.buttonA)
        quizControl()
    }

    fun buttonB() {
        correctQuiz(binding.buttonB)
        quizControl()
    }

    fun buttonC() {
        correctQuiz(binding.buttonC)
        quizControl()
    }

    fun buttonD() {
        correctQuiz(binding.buttonD)
        quizControl()
    }
}