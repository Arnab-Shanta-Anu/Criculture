package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.Batting

class BattingRVAdapter(
    val context: Context,
    val dataSet: List<Batting>
): RecyclerView.Adapter<BattingRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val playerNameTV: TextView = itemView.findViewById(R.id.player_name_TV)
        val runsTV: TextView = itemView.findViewById(R.id.runs_TV)
        val oversTV: TextView = itemView.findViewById(R.id.overs_TV)
        val noOf4TV: TextView = itemView.findViewById(R.id.no_of_4_TV)
        val noOf6TV: TextView = itemView.findViewById(R.id.no_of_6_TV)
        val strikeRateTV: TextView = itemView.findViewById(R.id.sr_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.match_score_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.playerNameTV.text = item.batsman.firstname
        holder.runsTV.text = item.score.toString()
        holder.oversTV.text = item.ball.toString()
        holder.noOf4TV.text = item.four_x.toString()
        holder.noOf6TV.text = item.six_x.toString()
        holder.strikeRateTV.text = item.rate.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}