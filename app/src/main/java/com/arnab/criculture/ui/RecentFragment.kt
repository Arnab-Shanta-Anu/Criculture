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
import com.arnab.criculture.adapters.UpcomingMatchesRVAdapter
import com.arnab.criculture.databinding.FragmentMenRankingBinding
import com.arnab.criculture.databinding.FragmentRecentBinding
import com.arnab.criculture.viewmodel.CricultureViewModel

class RecentFragment : Fragment() {
    lateinit var binding: FragmentRecentBinding
    lateinit var viewModel: CricultureViewModel

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
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecentBinding.bind(view)
        val recyclerView: RecyclerView = binding.recentMatchesRV

        viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        viewModel.recentMatches.observe(viewLifecycleOwner) {
            it?.let {
                recyclerView.adapter =
                    UpcomingMatchesRVAdapter(requireContext(), it.data)
            }
            recyclerView.layoutManager =
                LinearLayoutManager(requireContext())
            LinearSnapHelper().attachToRecyclerView(recyclerView)
        }
    }
}