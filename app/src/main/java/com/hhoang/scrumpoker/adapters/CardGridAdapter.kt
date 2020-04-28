package com.hhoang.scrumpoker.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hhoang.scrumpoker.R
import com.hhoang.scrumpoker.helpers.ResourceHelper
import com.hhoang.scrumpoker.model.CardScore
import com.hhoang.scrumpoker.model.ScrumPokerViewModel

class CardGridAdapter(
    private var cardScores: MutableList<CardScore>,
    private val viewModel: ScrumPokerViewModel,
    private val itemLayout: Int = R.layout.card_item_view
) :
    RecyclerView.Adapter<CardViewHolder>() {

    lateinit var context: Context
    lateinit var itemOnClick: (View, CardScore) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        context = parent.context
        val inflaterView = LayoutInflater.from(context)
        val itemView = inflaterView.inflate(itemLayout, parent, false)
        return CardViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cardScores.size
    }

    override fun onBindViewHolder(cardHolder: CardViewHolder, position: Int) {
        val cardScore = cardScores[position]
        val rh = ResourceHelper(context, viewModel.sizingMode.value, cardScore.cardTag)
        cardHolder.imageView.setImageResource(rh.drawableResourceId())
        if (cardScore.cardTag != "blank") {
            cardHolder.imageView.setOnClickListener { v -> itemOnClick(v, cardScore) }
        }
    }
}