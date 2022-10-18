package com.pcx.capitalofapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.data.DatabaseHelper
import com.pcx.capitalofapp.data.Quiz
import com.pcx.capitalofapp.data.QuizDao
import com.pcx.capitalofapp.databinding.FragmentFlagsBinding
import com.pcx.capitalofapp.ui.viewmodel.FlagsViewModel

class FlagsFragment : Fragment(R.layout.fragment_flags) {

    private lateinit var binding: FragmentFlagsBinding
    private lateinit var viewModel: FlagsViewModel

    private lateinit var questions: ArrayList<Quiz>
    private lateinit var falseAnswer: ArrayList<Quiz>
    private lateinit var trueAnswer: Quiz
    private lateinit var allOptions: HashSet<Quiz>
    private lateinit var dbs: DatabaseHelper

    private var questionCounter = 0
    private var trueCounter = 0
    private var falseCounter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFlagsBinding.bind(view)
        binding.flagsFragment = this

        dbs = DatabaseHelper(requireActivity())
        questions = QuizDao().getRandom10Flag(dbs)

        binding.trueText = "0"
        binding.falseText = "0"

        getQuiz()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FlagsViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun getQuiz() {
        trueAnswer = questions[questionCounter]
        falseAnswer = QuizDao().getRandom3WrongAnswer(dbs, trueAnswer.id)

        allOptions = HashSet()
        allOptions.apply {
            add(trueAnswer)
            add(falseAnswer[0])
            add(falseAnswer[1])
            add(falseAnswer[2])
        }

        binding.apply {
            buttonA.text = allOptions.elementAt(0).name
            buttonB.text = allOptions.elementAt(1).name
            buttonC.text = allOptions.elementAt(2).name
            buttonD.text = allOptions.elementAt(3).name

            meterText = "${questionCounter + 1}/10"

            ivFlag.setImageResource(
                resources.getIdentifier(
                    trueAnswer.flag, "drawable",
                    requireActivity().packageName
                )
            )
        }
    }

    private fun quizControl() {
        questionCounter++

        if (questionCounter != 10) {
            getQuiz()
        } else {
            val action = FlagsFragmentDirections.fromFlagsToResult(trueF = trueCounter)
            findNavController().navigate(action)
        }
    }

    private fun correctQuiz(button: Button) {
        val buttonText = button.text.toString()
        val correctAnswer = trueAnswer.name

        if (buttonText == correctAnswer) {
            trueCounter++
        } else {
            falseCounter++
        }

        binding.trueText = "$trueCounter"
        binding.falseText = "$falseCounter"
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