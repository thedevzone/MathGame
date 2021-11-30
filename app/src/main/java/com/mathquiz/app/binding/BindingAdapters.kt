package com.mathquiz.app.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mathquiz.app.R
import com.mathquiz.app.model.Operation
import com.mathquiz.app.model.QuizResult
import com.mathquiz.app.ui.home.adapters.ResultAdapter

@BindingAdapter("bindOperationImage")
fun bindOperationImage(view: AppCompatImageView, operation: Operation) {
    val image = when (operation) {
        Operation.ADDITION -> R.drawable.ic_add
        Operation.SUBTRACTION -> R.drawable.ic_minus
        Operation.MULTIPLICATION -> R.drawable.ic_multiplication
        Operation.DIVISION -> R.drawable.ic_division
    }
    view.setImageResource(image)
}

@BindingAdapter("bindResultImage")
fun bindResultImage(view: AppCompatImageView, result: QuizResult) {
    if (result.answer == result.yourAnswer)
        view.setImageResource(R.drawable.ic_right)
    else
        view.setImageResource(R.drawable.ic_wrong)
}

@BindingAdapter("bindRecyclerView")
fun bindRecyclerView(view: RecyclerView, list: List<QuizResult>?) {
    if (list != null) {
        val adapter = ResultAdapter()
        view.adapter = adapter
        adapter.updateResults(list)
    }
}

@BindingAdapter("bindQuestionNumber")
fun bindQuestionNumber(view: AppCompatTextView, result: QuizResult) {
    val operation = when (result.operation) {
        Operation.ADDITION -> "+"
        Operation.SUBTRACTION -> "-"
        Operation.MULTIPLICATION -> "*"
        Operation.DIVISION -> "/"
    }
    view.text =
        "Q".plus(result.id)
            .plus(" (")
            .plus(result.firstNumber)
            .plus(operation)
            .plus(result.secondNumber)
            .plus(")")
}