package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hhoang.scrumpoker.adapters.CardGridAdapter
import com.hhoang.scrumpoker.layoutmanager.CommonItemDecorationImpl
import com.hhoang.scrumpoker.model.CardScore
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.SizingMode
import com.hhoang.scrumpoker.model.ViewMode

class GridTshirtStartFragment : BaseFragment() {

    private var cardScores = initTshirtCards()
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val actionScore = GridTshirtStartFragmentDirections.actionGridTshirtStartFragmentToCardScoreFragment()
    private val actionPokerGridStart = GridTshirtStartFragmentDirections.actionGridTshirtStartFragmentToStartUpFragment()
    private val actionTshirtSwipeStart = GridTshirtStartFragmentDirections.actionGridTshirtStartFragmentToScrollStartFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflaterView = inflater.inflate(R.layout.fragment_grid_tshirt_start, container, false)
        val recyclerView = inflaterView.findViewById<RecyclerView>(R.id.viewGridTshirtStart)
        recyclerView.layoutManager = GridLayoutManager(inflaterView.context, 3, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(CommonItemDecorationImpl(top = 100))
        initAdapter(recyclerView).also { setOnViewModeChanged() }.also { setOnSizingModeChanged() }
        return inflaterView
    }

    private fun initTshirtCards(): MutableList<CardScore> {
        return mutableListOf(
            CardScore("xs"),
            CardScore("s"),
            CardScore("m"),
            CardScore("l"),
            CardScore("xl"),
            CardScore("xxl")
        )
    }

    private fun initAdapter(recycleView: RecyclerView) {
        val adapter = CardGridAdapter(cardScores, viewModel, R.layout.card_item_smaller_view)
        recycleView.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycleView)
        adapter.itemOnClick = { _: View, cs: CardScore ->
            viewModel.cardScore = cs.cardTag
            findNavController().navigate(actionScore)
        }
    }

    override fun setOnViewModeChanged() {
        viewModel.viewMode.observe(viewLifecycleOwner, Observer { viewMode ->
            if (viewMode == ViewMode.SWIPE) {
                findNavController().navigate(actionTshirtSwipeStart)
            }
        })
    }

    override fun setOnSizingModeChanged() {
        viewModel.sizingMode.observe(viewLifecycleOwner, Observer { sizingMode ->
            if (sizingMode == SizingMode.POKER_CARD) {
                findNavController().navigate(actionPokerGridStart)
            }
        })
    }
}
