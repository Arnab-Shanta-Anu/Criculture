package com.arnab.criculture.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.adapters.PlayersRVAdapter
import com.arnab.criculture.models.fixtures.Lineup
import com.arnab.criculture.viewmodel.CricultureViewModel

class PlayersFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playersListRecyclerView: RecyclerView = view.findViewById(R.id.players_list_RV)
        val viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        val playersList = mutableListOf<Lineup>()
        viewModel.upcomingMatches.observe(viewLifecycleOwner){
            it.data.forEach {
                playersList.addAll(it.lineup)
            }
            playersListRecyclerView.adapter = PlayersRVAdapter(requireContext(),playersList)
            playersListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

    }
}