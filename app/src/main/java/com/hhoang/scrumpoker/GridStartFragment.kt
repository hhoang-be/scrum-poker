package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hhoang.scrumpoker.model.ScrumPokerViewModel
import com.hhoang.scrumpoker.model.ViewMode

class GridStartFragment : Fragment(), View.OnClickListener {

    private var inflaterView: View? = null
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val actionBack = GridStartFragmentDirections.actionStartUpFragmentToCardScoreFragment()
    private val actionScrollStart = GridStartFragmentDirections.actionStartUpFragmentToScrollStartFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflaterView = inflater.inflate(R.layout.fragment_start_up, container, false)
        inflaterView.let {
            it?.findViewById<ImageView>(R.id.imgPoint_1)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_2)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_3)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_5)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_8)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_13)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_20)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_40)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_100)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_infinity)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_Question)?.setOnClickListener(this)
            it?.findViewById<ImageView>(R.id.imgPoint_Shaving)?.setOnClickListener(this)
        }.also { setOnViewModeChanged() }
        return this.inflaterView
    }

    private fun setOnViewModeChanged() {
        viewModel.viewMode.observe(viewLifecycleOwner, Observer { viewMode ->
            if (viewMode == ViewMode.SCROLL) {
                findNavController().navigate(actionScrollStart)
            }
        })
    }

    override fun onClick(view: View?) {
        val imageView = view as ImageView
        viewModel.cardScore = imageView.tag.toString()
        findNavController().navigate(actionBack)
    }
}
