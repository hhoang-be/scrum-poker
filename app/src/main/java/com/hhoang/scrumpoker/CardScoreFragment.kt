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
import com.hhoang.scrumpoker.model.ViewMode

class CardScoreFragment : Fragment() {

    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var imgCardScore: ImageView
    private lateinit var txtExplanation: TextView
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val action = CardScoreFragmentDirections.actionCardScoreFragmentToStartUpFragment()
    private val anotherStartAction = CardScoreFragmentDirections.actionCardScoreFragmentToScrollStartFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDetector = GestureDetectorCompat(context, OnSimpleGestureListenerImp())
        val inflaterView = inflater.inflate(R.layout.fragment_card_score, container, false)
        with(inflaterView) {
            imgCardScore = findViewById(R.id.imgCardScore)
            txtExplanation = findViewById(R.id.txtExplanation)
            updateCardScore(this)
            setViewTouchEventListener(this, mDetector)
        }
        return inflaterView
    }

    private fun setViewTouchEventListener(inflaterView: View, mDetector: GestureDetectorCompat) {
        inflaterView.setOnTouchListener { _: View, m: MotionEvent ->
            if (mDetector.onTouchEvent(m)) {
                true
            } else {
                inflaterView.onTouchEvent(m)
            }
        }
    }

    private fun updateCardScore(inflaterView: View) {
        val resourceHelper = ResourceHelper(inflaterView.context, viewModel.collector, viewModel.cardScore)
        imgCardScore.setImageResource(resourceHelper.drawableResourceId())
        txtExplanation.text = resourceHelper.literalString()
    }

    inner class OnSimpleGestureListenerImp : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            if (velocityX > 0) {
                if (viewModel.viewMode.value == ViewMode.GRID) {
                    findNavController().navigate(action)
                } else {
                    findNavController().navigate(anotherStartAction)
                }
                return true
            }
            return super.onFling(e1, e2, velocityX, velocityY)
        }
    }
}
