package com.hhoang.scrumpoker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScrumPokerViewModel : ViewModel() {
    var cardScore: String = "infinity"
    var sizingMode: MutableLiveData<SizingMode> = MutableLiveData(SizingMode.POKER_CARD)
    var viewMode: MutableLiveData<ViewMode> = MutableLiveData(ViewMode.GRID)
    var darkThemeMode: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setViewMode(mode: ViewMode) {
        viewMode.value = mode
    }

    fun setSizingMode(mode: SizingMode) {
        sizingMode.value = mode
    }

    fun setDarkThemeMode(darkMode: Boolean) {
        darkThemeMode.value = darkMode
    }
}