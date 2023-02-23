package com.arnab.criculture.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.adapters.MenRankingRVAdapter
import com.arnab.criculture.databinding.FragmentMenRankingBinding
import com.arnab.criculture.models.ranking.Team
import com.arnab.criculture.viewmodel.CricultureViewModel

class WomenRankingFragment : Fragment() {
    lateinit var viewModel: CricultureViewModel
    lateinit var binding: FragmentMenRankingBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        viewModel.getRanking()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_men_ranking, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenRankingBinding.bind(view)

        val recyclerView: RecyclerView = binding.menRankingRV
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        binding.btnTestRankingMen.visibility = View.GONE

        binding.btnOdiRankingMen.setOnClickListener {
            viewModel._odiRankingWomen.observe(viewLifecycleOwner) { it ->
                val teams = mutableListOf<Team>()
                it.data.forEach { it ->
                    it.team.forEach {
                        teams.add(it)
                    }
                }
                recyclerView.adapter = MenRankingRVAdapter(requireContext(), teams)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        binding.btnT20RankingMen.setOnClickListener {
            viewModel._t20RankingWomen.observe(viewLifecycleOwner) { it ->
                val teams = mutableListOf<Team>()
                it.data.forEach { it ->
                    it.team.forEach {
                        teams.add(it)
                    }
                }
                recyclerView.adapter = MenRankingRVAdapter(requireContext(), teams)
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        binding.btnOdiRankingMen.performClick()
    }
}