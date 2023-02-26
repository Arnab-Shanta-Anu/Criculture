package com.arnab.criculture.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.adapters.TeamsRVAdapter
import com.arnab.criculture.viewmodel.CricultureViewModel

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        val liveMatchRecyclerView: RecyclerView = view.findViewById(R.id.teams_RV)

        viewModel.allTeams.observe(viewLifecycleOwner) {
            it?.let {
                liveMatchRecyclerView.adapter = TeamsRVAdapter(requireContext(), it.data)
            }
            liveMatchRecyclerView.layoutManager =
                LinearLayoutManager(requireContext())
            LinearSnapHelper().attachToRecyclerView(liveMatchRecyclerView)
        }
    }
}