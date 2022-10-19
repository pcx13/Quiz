package com.pcx.capitalofapp.ui.viewmodel

import android.widget.Button
import androidx.lifecycle.ViewModel
import com.pcx.capitalofapp.data.DatabaseHelper
import com.pcx.capitalofapp.data.Quiz
import com.pcx.capitalofapp.data.QuizDao

class FlagsViewModel : ViewModel() {

    lateinit var questions: ArrayList<Quiz>
    lateinit var falseAnswer: ArrayList<Quiz>
    lateinit var trueAnswer: Quiz
    lateinit var allOptions: HashSet<Quiz>
    lateinit var dbs: DatabaseHelper

    var questionCounter = 0
    var trueCounter = 0
    var falseCounter = 0

    var outcomeT = "0"
    var outcomeF = "0"
    var counter = "0"

    fun correction(button: Button) {
        val buttonText = button.text.toString()
        val correctAnswer = trueAnswer.name

        if (buttonText == correctAnswer) {
            trueCounter++
        } else {
            falseCounter++
        }
        outcomeT = trueCounter.toString()
        outcomeF = falseCounter.toString()
    }

    fun quizzes() {
        trueAnswer = questions[questionCounter]
        falseAnswer = QuizDao().getRandom3WrongAnswer(dbs, trueAnswer.id)
        counter = "${questionCounter + 1}/10"

        allOptions = HashSet()
        allOptions.apply {
            add(trueAnswer)
            add(falseAnswer[0])
            add(falseAnswer[1])
            add(falseAnswer[2])
        }
    }
}