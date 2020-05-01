package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hhoang.scrumpoker.adapters.CardGridAdapter
import com.hhoang.scrumpoker.helpers.CardUtils
import com.hhoang.scrumpoker.layoutmanager.CenterZoomedLayoutManager
import com.hhoang.scrumpoker.model.CardScore
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.SizingMode
import com.hhoang.scrumpoker.model.ViewMode

private const val INITIAL_POSITION: Int = 3

class SwipeStartFragment : BaseFragment() {

    private var cardScores = CardUtils.initPokerCards()
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val actionScore = SwipeStartFragmentDirections.actionScrollStartFragmentToCardScoreFragment()
    private val actionPokerGridStart = SwipeStartFragmentDirections.actionScrollStartFragmentToStartUpFragment()
    private val actionTshirtGridStart =
        SwipeStartFragmentDirections.actionScrollStartFragmentToGridTshirtStartFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflaterView = inflater.inflate(R.layout.fragment_swipe_start, container, false)
        val recycleView = inflaterView.findViewById<RecyclerView>(R.id.swipeRecycleView)
        recycleView.let {
            initLayoutManager(it)
            initAdapter(it)
        }.also { setOnViewModeChanged() }.also { setOnSizingModeChanged() }
        return inflaterView
    }

    private fun initLayoutManager(recycleView: RecyclerView) {
        val layoutManager = CenterZoomedLayoutManager(inflaterView.context)
        recycleView.layoutManager = layoutManager
        recycleView.smoothScrollToPosition(INITIAL_POSITION)
    }

    private fun initAdapter(recycleView: RecyclerView) {
        val adapter = CardGridAdapter(cardScores, viewModel)
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
            if (viewMode == ViewMode.GRID && viewModel.sizingMode.value == SizingMode.POKER_CARD) {
                findNavController().navigate(actionPokerGridStart)
            } else if (viewMode == ViewMode.GRID && viewModel.sizingMode.value == SizingMode.T_SHIRT) {
                findNavController().navigate(actionTshirtGridStart)
            }
        })
    }

    override fun setOnSizingModeChanged() {
        viewModel.sizingMode.observe(viewLifecycleOwner, Observer { sm ->
            if (findNavController().currentDestination?.id == R.id.scrollStartFragment) {
                if (viewModel.viewMode.value == ViewMode.GRID && sm == SizingMode.POKER_CARD) {
                    findNavController().navigate(actionPokerGridStart)
                } else if (viewModel.viewMode.value == ViewMode.GRID && sm == SizingMode.T_SHIRT) {
                    findNavController().navigate(actionTshirtGridStart)
                } else if (viewModel.viewMode.value == ViewMode.SWIPE) {
                    cardScores.clear()
                    cardScores.addAll(
                        if (sm == SizingMode.POKER_CARD) {
                            CardUtils.initPokerCards()
                        } else {
                            CardUtils.initTshirtCards()
                        }
                    )
                    inflaterView.findViewById<RecyclerView>(R.id.swipeRecycleView).adapter?.notifyDataSetChanged()
                }
            }
        })
    }
}
