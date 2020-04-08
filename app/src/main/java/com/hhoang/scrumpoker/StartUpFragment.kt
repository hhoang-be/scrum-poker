package com.hhoang.scrumpoker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.hhoang.scrumpoker.model.ScrumPokerViewModel

class StartUpFragment : Fragment(), View.OnClickListener {

    private var inflaterView: View? = null
    private val viewModel: ScrumPokerViewModel by activityViewModels()
    private val action = StartUpFragmentDirections.actionStartUpFragmentToCardScoreFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.inflaterView = inflater.inflate(R.layout.fragment_start_up, container, false)
        inflaterView.let {
            it?.findViewById<Button>(R.id.btnButton0)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton1)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton2)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton3)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton5)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton8)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton13)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton20)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton40)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButton100)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButtonCoffee)?.setOnClickListener (this)
            it?.findViewById<Button>(R.id.btnButtonQuestion)?.setOnClickListener (this)
        }
        return this.inflaterView
    }

    override fun onClick(v: View?) {
        val button = v as Button
        viewModel.cardScore = button.tag.toString().toInt()
        findNavController().navigate(action)
    }
}
