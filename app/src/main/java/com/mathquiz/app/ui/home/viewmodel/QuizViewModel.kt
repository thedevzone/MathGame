package com.mathquiz.app.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mathquiz.app.model.Operation
import com.mathquiz.app.model.QuizResult
import com.mathquiz.app.utils.RandomUtils
import com.mathquiz.app.utils.value

class QuizViewModel : ViewModel() {

    private val results = ArrayList<QuizResult>()

    private val _firstNumber = MutableLiveData(0)
    val firstNumber = _firstNumber

    private val _secondNumber = MutableLiveData(0)
    val secondNumber = _secondNumber

    private val _operation = MutableLiveData(Operation.ADDITION)
    val operation = _operation

    private var answer: Int = 0

    private val _firstAnswer = MutableLiveData(0)
    val firstAnswer = _firstAnswer

    private val _secondAnswer = MutableLiveData(0)
    val secondAnswer = _secondAnswer

    private val _thirdAnswer = MutableLiveData(0)
    val thirdAnswer = _thirdAnswer

    private val _fourthAnswer = MutableLiveData(0)
    val fourthAnswer = _fourthAnswer

    private var quizCount = 0
    private val list = ArrayList<Int>()

    private val _quizNumber = MutableLiveData("")
    val quizNumber = _quizNumber

    val quizFinishedObserver = MutableLiveData<Unit>()
    val resetTimerObserver = MutableLiveData<Unit>()

    init {
        setupQuestion()
    }

    private fun setupQuestion() {
        if (quizCount >= 15) {
            quizFinishedObserver.postValue(Unit)
        } else {
            try {
                list.clear()
                resetTimerObserver.postValue(Unit)
                _firstNumber.value = RandomUtils.getRandom(0, 9)
                _operation.value = Operation.getOperation()
                _secondNumber.value = RandomUtils.getRandom(0, 9)
                answer = getAnswer()
                list.add(answer)
                setupOptions()
                quizCount++
                _quizNumber.value = "Q".plus(quizCount)
            } catch (e: Exception) {
                setupQuestion()
            }
        }
    }

    fun setupNewQuestion() {
        submitAnswer(-1)
    }

    private fun setupOptions() {
        val randomOptionNumber = RandomUtils.getRandom(0, 3)
        setupRandomOption(randomOptionNumber)
        when (randomOptionNumber) {
            0 -> _firstAnswer.value = answer
            1 -> _secondAnswer.value = answer
            2 -> _thirdAnswer.value = answer
            3 -> _fourthAnswer.value = answer
        }
    }

    private fun setupRandomOption(randomOptionNumber: Int) {
        if (randomOptionNumber != 0) {
            val option1 = RandomUtils.getRandomNumber(list, 16, answer)
            list.add(option1)
            _firstAnswer.value = option1
        }
        if (randomOptionNumber != 1) {
            val option2 = RandomUtils.getRandomNumber(list, 13, answer)
            list.add(option2)
            _secondAnswer.value = option2
        }
        if (randomOptionNumber != 2) {
            val option3 = RandomUtils.getRandomNumber(list, 22, answer)
            list.add(option3)
            _thirdAnswer.value = option3
        }
        if (randomOptionNumber != 3) {
            val option4 = RandomUtils.getRandomNumber(list, 8, answer)
            list.add(option4)
            _fourthAnswer.value = option4
        }
    }

    private fun getAnswer(): Int {
        val num1 = _firstNumber.value()
        val num2 = _secondNumber.value()
        return when (_operation.value ?: Operation.ADDITION) {
            Operation.ADDITION -> num1 + num2
            Operation.SUBTRACTION -> num1 - num2
            Operation.MULTIPLICATION -> num1 * num2
            Operation.DIVISION -> num1 / num2
        }
    }

    fun getResults(): ArrayList<QuizResult> = results

    fun submitAnswer(option: Int) {
        val submittedAnswer = when (option) {
            0 -> _firstAnswer.value()
            1 -> _secondAnswer.value()
            2 -> _thirdAnswer.value()
            3 -> _fourthAnswer.value()
            else -> -20
        }
        results.add(
            QuizResult(
                quizCount,
                _firstNumber.value(),
                _secondNumber.value(),
                answer,
                submittedAnswer,
                _operation.value ?: Operation.ADDITION
            )
        )
        setupQuestion()
    }
}