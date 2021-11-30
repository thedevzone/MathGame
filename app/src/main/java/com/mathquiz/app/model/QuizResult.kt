package com.mathquiz.app.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizResult(
    val id:Int,
    val firstNumber: Int,
    val secondNumber: Int,
    val answer: Int,
    val yourAnswer: Int,
    val operation: Operation
) : Parcelable
