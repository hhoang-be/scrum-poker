package com.hhoang.scrumpoker.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScrumPokerViewModel : ViewModel() {
    var cardScore: String = "infinity"
    var collector: String = "basic"
    var viewMode: MutableLiveData<ViewMode> = MutableLiveData(ViewMode.GRID)

    fun setViewMode(mode: ViewMode) {
        viewMode.value = mode
    }
}