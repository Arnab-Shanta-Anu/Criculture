package com.arnab.criculture.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arnab.criculture.R
import com.arnab.criculture.databinding.FragmentMatchDetailsBinding

class MatchDetailsFragment : Fragment() {
    lateinit var binding: FragmentMatchDetailsBinding
    lateinit var team1Name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            team1Name = it.getString("localTeamName", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMatchDetailsBinding.bind(view)

        binding.team1Name.text = team1Name
    }
}