package com.hhoang.scrumpoker

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.hhoang.scrumpoker.convertor.ResourceHelper
import com.hhoang.scrumpoker.layoutmanager.CenterZoomedLayoutManager
import com.hhoang.scrumpoker.model.CardScore
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.ViewMode


class SwipeStartFragment : Fragment() {

    private lateinit var inflaterView: View
    private var cardScores = initPokerCards()
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val actionBack = SwipeStartFragmentDirections.actionScrollStartFragmentToCardScoreFragment()
    private val actionStartUp = SwipeStartFragmentDirections.actionScrollStartFragmentToStartUpFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflaterView = inflater.inflate(R.layout.fragment_swipe_start, container, false)
        val recycleView = inflaterView.findViewById<RecyclerView>(R.id.my_recycler_view)
        recycleView.let {
            initLayoutManager(it)
            initAdapter(it)
        }.also { setOnViewModeChanged() }
        return inflaterView
    }

    private fun initLayoutManager(recycleView: RecyclerView) {
        val layoutManager = CenterZoomedLayoutManager(inflaterView.context)
        recycleView.layoutManager = layoutManager
        recycleView.smoothScrollToPosition(5)
    }

    private fun initAdapter(recycleView: RecyclerView) {
        val adapter = CardScoreAdapter(cardScores, viewModel)
        recycleView.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recycleView)
        adapter.itemOnClick = { _: View, cs: CardScore ->
            viewModel.cardScore = cs.cardTag
            findNavController().navigate(actionBack)
        }
    }

    private fun setOnViewModeChanged() {
        viewModel.viewMode.observe(viewLifecycleOwner, Observer { viewMode ->
            if (viewMode == ViewMode.GRID) {
                findNavController().navigate(actionStartUp)
            }
        })
    }

    private fun initPokerCards(): MutableList<CardScore> {
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

    class CardScoreAdapter(private var cardScores: MutableList<CardScore>, private val viewModel: ScrumPokerViewModel) :
        RecyclerView.Adapter<CardScoreAdapter.CardViewHolder>() {

        lateinit var context: Context
        lateinit var itemOnClick: (View, CardScore) -> Unit

        class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var imageView: ImageView = view.findViewById(R.id.itemImageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
            context = parent.context
            val inflaterView = LayoutInflater.from(context)
            val itemView = inflaterView.inflate(R.layout.card_item_view, parent, false)
            return CardViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return cardScores.size
        }

        override fun onBindViewHolder(cardHolder: CardViewHolder, position: Int) {
            val cardScore = cardScores[position]
            val rh = ResourceHelper(context, viewModel.collector, cardScore.cardTag)
            cardHolder.imageView.setImageResource(rh.drawableResourceId())
            if (position != 0 && position != cardScores.size - 1) {
                cardHolder.imageView.setOnClickListener { v -> itemOnClick(v, cardScore) }
            }
        }
    }
}
