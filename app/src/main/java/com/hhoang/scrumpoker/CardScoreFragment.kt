package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hhoang.scrumpoker.convertor.ResourceHelper
import com.hhoang.scrumpoker.model.ScrumPokerViewModel

class CardScoreFragment : Fragment() {

    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private lateinit var mDetector: GestureDetectorCompat
    private val action = CardScoreFragmentDirections.actionCardScoreFragmentToStartUpFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDetector = GestureDetectorCompat(context, OnSimpleGestureListenerImp())
        val inflaterView = inflater.inflate(R.layout.fragment_card_score, container, false)
        setViewTouchEventListener(inflaterView, mDetector)
        updateCardScore(inflaterView)
        return inflaterView
    }

    private fun setViewTouchEventListener(inflaterView: View, mDetector: GestureDetectorCompat) {
        inflaterView.setOnTouchListener { v: View, m: MotionEvent ->
            if (mDetector.onTouchEvent(m)) {
                true
            } else {
                inflaterView.onTouchEvent(m)
            }
        }
    }

    private fun updateCardScore(inflaterView: View) {
        val resourceHelper = ResourceHelper(inflaterView.context, viewModel.collector, viewModel.cardScore)
        val imgCardScore = inflaterView.findViewById(R.id.imgCardScore) as ImageView
        imgCardScore.setImageResource(resourceHelper.drawableResourceId())
        val txtExplanation = inflaterView.findViewById(R.id.txtExplanation) as TextView
        txtExplanation.text = resourceHelper.literalString()
    }

    inner class OnSimpleGestureListenerImp : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (velocityX > 0) {
                findNavController().navigate(action)
                return true
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}
