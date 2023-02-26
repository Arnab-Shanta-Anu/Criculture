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
import com.arnab.criculture.R
import com.arnab.criculture.adapters.RecentMatchesRVAdapter
import com.arnab.criculture.databinding.FragmentFixtureBinding
import com.arnab.criculture.viewmodel.CricultureViewModel

class FixtureFragment : Fragment() {
    private lateinit var viewModel: CricultureViewModel
    lateinit var binding: FragmentFixtureBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        viewModel.getLeagueFixture()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fixture, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFixtureBinding.bind(view)
        binding.fixtureRV.layoutManager = LinearLayoutManager(requireContext())
        binding.t20BTN.setOnClickListener {
            viewModel._t20FixtureData.observe(viewLifecycleOwner) {
                binding.fixtureRV.adapter = RecentMatchesRVAdapter(requireContext(), it.data)
            }
        }
        binding.bigBashBTN.setOnClickListener {
            viewModel._bigBashFixtureData.observe(viewLifecycleOwner) {
                binding.fixtureRV.adapter = RecentMatchesRVAdapter(requireContext(), it.data)
            }
        }
        binding.csaBTN.setOnClickListener {
            viewModel._csaFixtureData.observe(viewLifecycleOwner) {
                binding.fixtureRV.adapter = RecentMatchesRVAdapter(requireContext(), it.data)
            }
        }
    }
}