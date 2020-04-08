package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hhoang.scrumpoker.model.ScrumPokerViewModel

class CardScoreFragment : Fragment() {

    private val viewModel: ScrumPokerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterView = inflater.inflate(R.layout.fragment_card_score, container, false)
        val score = viewModel.cardScore
        updateCardScore(score, inflaterView)
        return inflaterView
    }

    private fun updateCardScore(score: String, view: View) {
        val context = view.context
        val imgCardScore = view.findViewById(R.id.imgCardScore) as ImageView
        val imageResId = context.resources.getIdentifier("col_1_" + score, "drawable", context.packageName)
        imgCardScore.setImageResource(imageResId)
        val stringResId = context.resources.getIdentifier("col_1_" + score, "string", context.packageName)
        val txtExplanation = view.findViewById(R.id.txtExplanation) as TextView
        txtExplanation.text = context.resources.getString(stringResId)
    }
}
