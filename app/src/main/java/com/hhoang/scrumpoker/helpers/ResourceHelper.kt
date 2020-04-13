package com.hhoang.scrumpoker.helpers

import android.content.Context
import com.hhoang.scrumpoker.model.SizingMode


class ResourceHelper(private val context: Context, sizingMode: SizingMode?, score: String) {

    private val qualifiedName = sizingMode?.drawablePrefix + "_" + score

    fun drawableResourceId(): Int {
        return context.resources.getIdentifier(qualifiedName, "drawable", context.packageName)
    }

    fun literalString(): String {
        val strResId = context.resources.getIdentifier(qualifiedName, "string", context.packageName)
        return context.resources.getString(strResId)
    }
}