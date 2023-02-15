package com.arnab.criculture.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.FixtureData
import com.arnab.criculture.viewmodel.CricultureViewModel
import com.bumptech.glide.Glide

class UpcomingMatchesRVAdapter(
    private val context: Context,
    private val dataSet: List<FixtureData>,
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        Glide.with(context)
            .load(item.localteam.image_path)
            .override(300, 300)
            .into(holder.team1Img)

        Glide.with(context)
            .load(item.visitorteam.image_path)
            .override(300, 300)
            .into(holder.team2Img)

        holder.timeAndDate.text = item.starting_at
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}