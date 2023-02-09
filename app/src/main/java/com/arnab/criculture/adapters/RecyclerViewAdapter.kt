package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.teams.TeamData
import com.bumptech.glide.Glide

class RecyclerViewAdapter(
    private val context: Context,
    private val teamDataSet: List<TeamData>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryImgView: ImageView = itemView.findViewById(R.id.country_flag_IMGView)
        val countryNameTV: TextView = itemView.findViewById(R.id.country_name_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = teamDataSet.get(position)
        Glide.with(context)
            .load(item.image_path)
            .override(150,150)
            .into(holder.countryImgView)
        holder.countryNameTV.text = item.name
    }

    override fun getItemCount(): Int {
        return teamDataSet.size
    }
}