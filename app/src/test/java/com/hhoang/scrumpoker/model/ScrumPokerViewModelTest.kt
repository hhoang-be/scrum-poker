package com.hhoang.scrumpoker.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ScrumPokerViewModelTest {

    @Test
    fun initialState() {
        val viewModel = ScrumPokerViewModel()
        assertThat(viewModel.viewMode.value).isEqualTo(ViewMode.GRID)
        assertThat(viewModel.sizingMode.value).isEqualTo(SizingMode.POKER_CARD)
        assertThat(viewModel.darkThemeMode.value).isEqualTo(false)
    }
}
