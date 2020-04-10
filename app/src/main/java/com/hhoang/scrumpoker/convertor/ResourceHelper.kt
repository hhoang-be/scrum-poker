package com.hhoang.scrumpoker.convertor

import android.content.Context


class ResourceHelper(private val context: Context, collector: String, score: String) {

    private val qualifiedName = collector + "_" + score

    fun drawableResourceId(): Int {
        return context.resources.getIdentifier(qualifiedName, "drawable", context.packageName)
    }

    fun literalString(): String {
        val strResId = context.resources.getIdentifier(qualifiedName, "string", context.packageName)
        return context.resources.getString(strResId)
    }
}