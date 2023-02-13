package com.arnab.criculture.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.UpcomingMatchData
import com.arnab.criculture.viewmodel.CricultureViewModel
import com.bumptech.glide.Glide

class UpcomingMatchesRVAdapter(
    private val context: Context,
    private val dataSet: List<UpcomingMatchData>,
    private val viewModel: CricultureViewModel
) : RecyclerView.Adapter<UpcomingMatchesRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val team1Img: ImageView = itemView.findViewById(R.id.team_1_IV)
        val team2Img: ImageView = itemView.findViewById(R.id.team_2_IV)
        val timeAndDate: TextView = itemView.findViewById(R.id.time_n_date_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.upcoming_matches_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
       // Log.d( "onBindViewHolder: local", item.localteam_id.toString())
       // Log.d( "onBindViewHolder: visit", item.visitorteam_id.toString())
        val team1 = item.localteam_id?.let {
            viewModel.getTeamById(it)
           // Log.d("onBindViewHolder: local",viewModel.getTeamById(it).toString())
            //Log.d("onBindViewHolder: local",viewModel.getTeamById(it).toString())
        }
        val team2 = item.visitorteam_id?.let {
            viewModel.getTeamById(it)
            //Log.d("onBindViewHolder: visit",viewModel.getTeamById(it).toString())
        }

        Log.d(
            "TEST",
            """onBindViewHolder: localteamid: ${item.localteam_id} visitorteamid: ${item.visitorteam_id}
                                                                team1Id: ${team1} team2Id: ${team2}
        """.trimMargin()
        )

        Glide.with(context)
            .load(team1?.image_path)
            .override(150, 150)
            .into(holder.team1Img)

        Glide.with(context)
            .load(team2?.image_path)
            .override(150, 150)
            .into(holder.team2Img)

        holder.timeAndDate.text = item.starting_at
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}