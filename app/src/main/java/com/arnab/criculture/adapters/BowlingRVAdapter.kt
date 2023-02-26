package com.arnab.criculture.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.Bowling
import org.w3c.dom.Text

class BowlingRVAdapter(
    val context: Context,
    private val dataSet: List<Bowling>
) : RecyclerView.Adapter<BowlingRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerNameTV: TextView = itemView.findViewById(R.id.player_name_TV)
        val oversTV: TextView = itemView.findViewById(R.id.overs_TV)
        val runsTV: TextView = itemView.findViewById(R.id.runs_TV)
        val wicketsTV: TextView = itemView.findViewById(R.id.wickets_TV)
        val economyTV: TextView = itemView.findViewById(R.id.economy_TV)
        val maidenTV: TextView = itemView.findViewById(R.id.maidens_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bowling_scoreboard_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.playerNameTV.text = item.bowler.fullname
        holder.oversTV.text = item.overs.toString()
        holder.wicketsTV.text = item.wickets.toString()
        holder.runsTV.text = item.runs.toString()
        holder.economyTV.text = item.rate.toString()
        holder.maidenTV.text = item.medians.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}