package com.oseketechnologies.viewmodelguess_wordgame.screens.title.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.oseketechnologies.viewmodelguess_wordgame.R
import com.oseketechnologies.viewmodelguess_wordgame.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var viewModel : ScoreViewModel
    private lateinit var viewModelFactory : ScoreViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater, R.layout.fragment_score, container, false)
        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(arguments!!).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAGain->
            if(playAGain){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onEventPlayAgainCompleted()
            }
        })

        return binding.root
    }


}