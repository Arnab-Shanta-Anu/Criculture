package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.FixtureData
import com.bumptech.glide.Glide

class UpcomingMatchesAdapter(
    val context: Context,
    private val dataSet: List<FixtureData>
): RecyclerView.Adapter<UpcomingMatchesAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val team1ImgView: ImageView = itemView.findViewById(R.id.team_1_IV)
        val team2ImgView: ImageView = itemView.findViewById(R.id.team_2_IV)
        val timeAndDateTV: TextView = itemView.findViewById(R.id.time_n_date_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.upcoming_matches_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        Glide.with(context)
            .load(item.localteam.image_path)
            .into(holder.team1ImgView)
        Glide.with(context)
            .load(item.visitorteam.image_path)
            .into(holder.team2ImgView)
        holder.timeAndDateTV.text = item.starting_at.split("T")[0]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}