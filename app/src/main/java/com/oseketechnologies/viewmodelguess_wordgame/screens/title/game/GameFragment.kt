package com.oseketechnologies.viewmodelguess_wordgame.screens.title.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.oseketechnologies.viewmodelguess_wordgame.R
import com.oseketechnologies.viewmodelguess_wordgame.databinding.FragmentGameBinding
import java.util.EnumSet.of


class GameFragment : Fragment() {

    private lateinit var  binding : FragmentGameBinding
    private lateinit var viewModel : GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.isListEmpty.observe(viewLifecycleOwner, Observer { isEmpty ->
            if(isEmpty){
                endGame()
            }
        })

        return binding.root
    }


    private fun endGame(){
        gameFinished()
    }

    private fun gameFinished(){
        Toast.makeText(activity, "game just finished", Toast.LENGTH_SHORT).show()
        viewModel.onGameFinishComplete()
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(
            viewModel.score.value?: 0
        )
        NavHostFragment.findNavController(this).navigate(action)
    }







}