package com.hhoang.scrumpoker.layoutmanager

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CenterZoomedLayoutManager(context: Context) :
    LinearLayoutManager(context, HORIZONTAL, false) {

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return if (orientation == HORIZONTAL) {
            super.scrollHorizontallyBy(dx, recycler, state).also { scaleChildren() }
        } else {
            0
        }
    }

    private val mShrinkDistance: Float = 0.90F
    private val mShrinkAmount: Float = 0.50F

    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = mShrinkDistance * midpoint
        for (i in 0 until childCount) {
            val child = getChildAt(i) as View
            val d = d1.coerceAtMost(
                abs(
                    midpoint - (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f
                )
            )
            val scale = 1f - mShrinkAmount * d / d1
            child.scaleX = scale
            child.scaleY = scale
        }
    }
}