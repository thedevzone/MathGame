package com.mathquiz.app.utils

import android.content.Context
import android.view.View
import androidx.annotation.ColorRes
import androidx.lifecycle.MutableLiveData

/**
 * User This Extension For Handle Multiple Click..
 */
fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun MutableLiveData<Int>.value() = this.value ?: 0

fun Context.getColorFromResource(@ColorRes colorInt: Int): Int =
    this.resources.getColor(colorInt, theme)