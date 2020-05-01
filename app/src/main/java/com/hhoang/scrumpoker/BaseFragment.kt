package com.hhoang.scrumpoker

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    protected lateinit var inflaterView: View

    abstract fun setOnViewModeChanged()
    abstract fun setOnSizingModeChanged()

}