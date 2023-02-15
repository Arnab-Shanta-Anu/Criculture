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
import com.arnab.criculture.models.fixtures.FixtureData
import com.arnab.criculture.models.fixtures.Lineup
import com.bumptech.glide.Glide

private const val TAG = "PlayersRVAdapter"
class PlayersRVAdapter(
    private val context: Context,
    private val dataSet: List<Lineup>
): RecyclerView.Adapter<PlayersRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val playerImage: ImageView = itemView.findViewById(R.id.player_image_IV)
        val playerName: TextView = itemView.findViewById(R.id.player_name_TV)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.players_list, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        Log.d(TAG, "onBindViewHolder: Item - ${item.country_id}")
        /*Glide.with(context)
            .load(item.image_path)
            .override(150,150)
            .into(holder.playerImage)
        holder.playerName.text = item.fullname*/
    }
    override fun getItemCount(): Int {
        return dataSet.size
    }
}