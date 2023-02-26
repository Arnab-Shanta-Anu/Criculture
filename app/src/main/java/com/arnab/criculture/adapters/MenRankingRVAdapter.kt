package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.ranking.Team
import com.bumptech.glide.Glide

class MenRankingRVAdapter(
    val context: Context,
    private val dataSet: List<Team>
): RecyclerView.Adapter<MenRankingRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val teamImageView: ImageView = itemView.findViewById(R.id.team_IV)
        val teamName: TextView = itemView.findViewById(R.id.team_name_TV)
        val teamRanking: TextView = itemView.findViewById(R.id.team_ranking_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.ranking_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        Glide.with(context)
            .load(item.image_path)
            .override(150,150)
            .into(holder.teamImageView)
        holder.teamName.text = item.name
        holder.teamRanking.text = item.ranking.position.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}