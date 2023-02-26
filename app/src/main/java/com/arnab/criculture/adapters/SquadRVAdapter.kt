package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.Lineup
import com.bumptech.glide.Glide

class SquadRVAdapter(
    val context: Context,
    val dataSet: List<Lineup>
): RecyclerView.Adapter<SquadRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val team1PlayerImgView: ImageView = itemView.findViewById(R.id.team1_player_IV)
        val team2PlayerImgView: ImageView = itemView.findViewById(R.id.team2_player_IV)
        val team1PlayerNameTV: TextView = itemView.findViewById(R.id.team1_player_name_TV)
        val team2PlayerNameTV: TextView = itemView.findViewById(R.id.team2_player_name_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.squad_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        val item2 = dataSet[position+11]
        Glide.with(context)
            .load(item.image_path)
            .into(holder.team1PlayerImgView)
        Glide.with(context)
            .load(item2.image_path)
            .into(holder.team2PlayerImgView)
        holder.team1PlayerNameTV.text = item.fullname
        holder.team2PlayerNameTV.text = item2.fullname
    }

    override fun getItemCount(): Int {
        return (dataSet.size/2)
    }
}