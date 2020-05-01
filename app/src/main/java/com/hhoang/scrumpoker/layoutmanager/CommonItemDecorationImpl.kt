package com.hhoang.scrumpoker.layoutmanager

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CommonItemDecorationImpl(var top: Int = 0, var bottom: Int = 0, var left: Int = 0, var right: Int = 0) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = top
        outRect.bottom = bottom
        outRect.left = left
        outRect.right = right
    }

}