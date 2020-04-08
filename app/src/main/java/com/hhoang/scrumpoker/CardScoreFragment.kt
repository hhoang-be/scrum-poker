package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hhoang.scrumpoker.model.ScrumPokerViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CardScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardScoreFragment : Fragment() {

    private val viewModel: ScrumPokerViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterView = inflater.inflate(R.layout.fragment_card_score, container, false)
        val score = viewModel.cardScore.toString()
        inflaterView.let {
            val txtCardScore = it!!.findViewById(R.id.txtCardScore) as TextView
            txtCardScore.text = score
        }
        return inflaterView
    }
}
