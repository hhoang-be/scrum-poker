package com.hhoang.scrumpoker.helpers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CardUtilsTest {

    @Test
    fun initPokerCards() {
        val pokerCards = CardUtils.initPokerCards()
        assertThat(pokerCards.size).isEqualTo(14)
    }

    @Test
    fun initTshirtCards() {
        val pokerCards = CardUtils.initTshirtCards()
        assertThat(pokerCards.size).isEqualTo(8)
    }

}