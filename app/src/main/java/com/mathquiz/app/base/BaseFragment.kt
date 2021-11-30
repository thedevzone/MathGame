package com.mathquiz.app.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun loadFragment(fragment: Fragment, isAddToBackStack: Boolean = false) {
        if (activity is BaseActivity)
            (activity as BaseActivity).loadFragment(fragment, isAddToBackStack)
    }

    fun goBack() {
        if (activity is BaseActivity)
            (activity as BaseActivity).goBack()
    }
}