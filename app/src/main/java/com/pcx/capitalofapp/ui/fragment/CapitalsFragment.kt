package com.pcx.capitalofapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pcx.capitalofapp.R
import com.pcx.capitalofapp.data.DatabaseHelper
import com.pcx.capitalofapp.data.Quiz
import com.pcx.capitalofapp.data.QuizDao
import com.pcx.capitalofapp.databinding.FragmentCapitalsBinding
import com.pcx.capitalofapp.ui.viewmodel.CapitalsViewModel

class CapitalsFragment : Fragment(R.layout.fragment_capitals) {

    private lateinit var binding: FragmentCapitalsBinding
    private lateinit var viewModel: CapitalsViewModel

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
        binding = FragmentCapitalsBinding.bind(view)
        binding.capitalsFragment = this

        dbs = DatabaseHelper(requireActivity())
        questions = QuizDao().getRandom10Flag(dbs)

        binding.trueText="0"
        binding.falseText="0"

        getQuiz()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: CapitalsViewModel by viewModels()
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
            buttonA1.text = allOptions.elementAt(0).capital
            buttonB1.text = allOptions.elementAt(1).capital
            buttonC1.text = allOptions.elementAt(2).capital
            buttonD1.text = allOptions.elementAt(3).capital

            countryText = trueAnswer.name
            meterText = "${questionCounter + 1}/10"

            ivFlag3.setImageResource(
                resources.getIdentifier(
                    trueAnswer.flag,
                    "drawable",
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
            val action = CapitalsFragmentDirections.fromCapitalsToResult(trueF = trueCounter)
            findNavController().navigate(action)
        }
    }

    private fun correctQuiz(button: Button) {
        val buttonText = button.text.toString()
        val correctAnswer = trueAnswer.capital

        if (buttonText == correctAnswer) {
            trueCounter++
        } else {
            falseCounter++
        }

        binding.trueText = "$trueCounter"
        binding.falseText = "$falseCounter"
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