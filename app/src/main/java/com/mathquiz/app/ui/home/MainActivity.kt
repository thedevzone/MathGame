package com.mathquiz.app.ui.home

import android.os.Bundle
import com.mathquiz.app.base.BaseActivity
import com.mathquiz.app.databinding.ActivityMainBinding
import com.mathquiz.app.ui.home.fragments.WelcomeFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(WelcomeFragment())
    }

}