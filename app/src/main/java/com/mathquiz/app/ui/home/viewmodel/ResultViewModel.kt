package com.mathquiz.app.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mathquiz.app.model.QuizResult

class ResultViewModel : ViewModel() {

    private val _results = MutableLiveData<List<QuizResult>>()
    val results = _results

    private val _score = MutableLiveData<String>("")
    val score = _score

    fun fillResults(list: List<QuizResult>) {
        _results.value = list
        manageScore()
    }

    private fun manageScore() {
        val rightAnswer = _results.value?.filter { it.answer == it.yourAnswer }
        _score.value = rightAnswer?.size.toString().plus("/").plus(_results.value?.size)
    }
}