package com.mathquiz.app.model

import com.mathquiz.app.utils.RandomUtils

enum class Operation {
    ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION;

    companion object {
        fun getOperation(): Operation {
            return when (RandomUtils.getRandom(0, 3)) {
                0 -> ADDITION
                1 -> SUBTRACTION
                2 -> MULTIPLICATION
                3 -> DIVISION
                else -> ADDITION
            }
        }
    }
}