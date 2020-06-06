package com.oseketechnologies.viewmodelguess_wordgame.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.oseketechnologies.viewmodelguess_wordgame.R
import com.oseketechnologies.viewmodelguess_wordgame.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,R.layout.fragment_title, container, false)
        binding.playGameButton.setOnClickListener { findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }
        return binding.root
    }
}


