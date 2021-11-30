package com.mathquiz.app.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mathquiz.app.R

open class BaseActivity : AppCompatActivity() {

    fun loadFragment(fragment: Fragment, isAddToBackStack: Boolean = false) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        if (isAddToBackStack)
            transaction.addToBackStack(fragment::class.java.name)
        transaction.commit()
    }

    fun goBack(){
        onBackPressed()
    }
}