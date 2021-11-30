package com.mathquiz.app.ui.launch

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mathquiz.app.R
import com.mathquiz.app.base.BaseActivity
import com.mathquiz.app.ui.home.MainActivity

class LaunchActivity : BaseActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val launchRunnable = {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(launchRunnable, DELAY)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(launchRunnable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    companion object {
        const val DELAY: Long = 2000
    }
}