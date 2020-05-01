package com.hhoang.scrumpoker.helpers

import com.hhoang.scrumpoker.model.CardScore

class CardUtils {

    companion object {
        fun initPokerCards(): MutableList<CardScore> {
            return mutableListOf(
                CardScore("blank"),
                CardScore("01"),
                CardScore("02"),
                CardScore("03"),
                CardScore("05"),
                CardScore("08"),
                CardScore("13"),
                CardScore("20"),
                CardScore("40"),
                CardScore("100"),
                CardScore("infinity"),
                CardScore("question"),
                CardScore("shaving"),
                CardScore("blank")
            )
        }

        fun initTshirtCards(): MutableList<CardScore> {
            return mutableListOf(
                CardScore("blank"),
                CardScore("xs"),
                CardScore("s"),
                CardScore("m"),
                CardScore("l"),
                CardScore("xl"),
                CardScore("xxl"),
                CardScore("blank")
            )
        }
    }
}