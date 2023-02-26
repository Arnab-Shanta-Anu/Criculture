package com.arnab.criculture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.arnab.criculture.R

class MoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playersTextView: TextView = view.findViewById(R.id.player_TV)
        val teamsTextView: TextView = view.findViewById(R.id.teams_TV)
        val menRankingTextView: TextView = view.findViewById(R.id.men_ranking_TV)
        val womenRankingTextView: TextView = view.findViewById(R.id.women_ranking_TV)

        playersTextView.setOnClickListener {
            val action = MoreFragmentDirections.actionMoreFragmentToPlayersFragment()
            findNavController().navigate(action)
        }
        teamsTextView.setOnClickListener {
            val action = MoreFragmentDirections.actionMoreFragmentToTeamsFragment()
            findNavController().navigate(action)
        }
        menRankingTextView.setOnClickListener {
            val action = MoreFragmentDirections.actionMoreFragmentToMenRankingFragment()
            findNavController().navigate(action)
        }
        womenRankingTextView.setOnClickListener {
            val action = MoreFragmentDirections.actionMoreFragmentToWomenRankingFragment()
            findNavController().navigate(action)
        }
    }
}