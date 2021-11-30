package com.mathquiz.app.utils

object RandomUtils {

    fun getRandomNumber(list: List<Int>, end: Int, plusNumber: Int = 0): Int {
        val number = (0..end).random() + plusNumber
        return if (list.contains(number))
            getRandomNumber(list, end, plusNumber)
        else
            number
    }

    fun getRandom(start: Int, end: Int): Int = (start..end).random()
}