package com.arnab.criculture.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.adapters.PlayersRVAdapter
import com.arnab.criculture.databinding.FragmentPlayersBinding
import com.arnab.criculture.models.fixtures.Lineup
import com.arnab.criculture.viewmodel.CricultureViewModel

private const val TAG = "PlayersFragment"

class PlayersFragment() : Fragment() {

    lateinit var binding: FragmentPlayersBinding
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
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPlayersBinding.bind(view)

        val searchView: SearchView = binding.searchView
        val playersListRecyclerView: RecyclerView = binding.playersListRV
        playersListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]
        val playerList = mutableSetOf<Lineup>()

        searchView.clearFocus()
        viewModel.recentMatches.observe(viewLifecycleOwner) { it ->
            it?.let { it ->
                it.data.forEach { it ->
                    it.lineup?.forEach {
                        playerList.add(it)
                        Log.d(TAG, "onViewCreated: $it")
                    }
                }
            }
            binding.progressBar.visibility = View.GONE
            playersListRecyclerView.adapter =
                PlayersRVAdapter(requireContext(), playerList.toList())
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                val filteredList = mutableSetOf<Lineup>()
                viewModel.recentMatches.observe(viewLifecycleOwner) { it ->
                    it?.data?.forEach { it ->
                        it.lineup?.forEach {
                            if (it.fullname.contains(query!!, ignoreCase = true)) {
                                filteredList.add(it)
                            }
                        }
                    }
                }
                playersListRecyclerView.adapter = PlayersRVAdapter(
                    requireContext(),
                    filteredList.toList()
                )
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    playersListRecyclerView.adapter = PlayersRVAdapter(
                        requireContext(), playerList.toList()
                    )
                }
                return true
            }
        })
    }
}