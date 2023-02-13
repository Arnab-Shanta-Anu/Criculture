package com.arnab.criculture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.adapters.TeamsRVAdapter
import com.arnab.criculture.adapters.UpcomingMatchesRVAdapter
import com.arnab.criculture.viewmodel.CricultureViewModel

class HomeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val liveMatchRecyclerView: RecyclerView = view.findViewById(R.id.live_match_RV)
        val upcomingMatchesRecyclerView: RecyclerView = view.findViewById(R.id.upcoming_match_RV)
        val viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]

        viewModel.allTeams.observe(viewLifecycleOwner) {
            it?.let {
                liveMatchRecyclerView.adapter = TeamsRVAdapter(requireContext(), it.data)
            }
            liveMatchRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
        }
        viewModel.upcomingMatches.observe(viewLifecycleOwner) {
            it?.let {
                upcomingMatchesRecyclerView.adapter =
                    UpcomingMatchesRVAdapter(requireContext(), it.data, viewModel)
            }
            upcomingMatchesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}