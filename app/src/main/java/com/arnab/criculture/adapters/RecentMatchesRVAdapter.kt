package com.arnab.criculture.adapters

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.R
import com.arnab.criculture.models.fixtures.FixtureData
import com.arnab.criculture.ui.RecentFragmentDirections
import com.bumptech.glide.Glide

private const val TAG = "RecentMatchesRVAdapter"

class RecentMatchesRVAdapter(
    private val context: Context,
    private val dataSet: List<FixtureData>,
) : RecyclerView.Adapter<RecentMatchesRVAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val team1Img: ImageView = itemView.findViewById(R.id.team_1_IV)
        val team2Img: ImageView = itemView.findViewById(R.id.team_2_IV)
        val matchVenueTV: TextView = itemView.findViewById(R.id.match_venue_TV)
        val matchResultTV: TextView = itemView.findViewById(R.id.match_result_TV)
        val team1RunsTV: TextView = itemView.findViewById(R.id.team_1_runs_TV)
        val team2RunsTV: TextView = itemView.findViewById(R.id.team_2_runs_TV)
        val team1BallsTv: TextView = itemView.findViewById(R.id.team_1_overs_TV)
        val team2BallsTv: TextView = itemView.findViewById(R.id.team_2_overs_TV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.live_matches_list, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        Glide.with(context)
            .load(item.localteam.image_path)
            .into(holder.team1Img)

        Glide.with(context)
            .load(item.visitorteam.image_path)
            .into(holder.team2Img)

        //holder.timeAndDate.text = item.starting_at.split('T')[0]
        holder.matchVenueTV.text = item.venue?.name
        holder.matchResultTV.text = item.note
        try {
            if (item.runs?.get(0)?.team_id == item.localteam_id) {
                holder.team1RunsTV.text =
                    "${item.runs?.get(0)?.score}/${item.runs?.get(0)?.wickets}"
                holder.team2RunsTV.text =
                    "${item.runs?.get(1)?.score}/${item.runs?.get(1)?.wickets}"
                holder.team1BallsTv.text = "Overs(${item.runs?.get(0)?.overs})"
                holder.team2BallsTv.text = "Overs(${item.runs?.get(1)?.overs})"
            } else {
                holder.team1RunsTV.text =
                    "${item.runs?.get(1)?.score}/${item.runs?.get(1)?.wickets}"
                holder.team2RunsTV.text =
                    "${item.runs?.get(0)?.score}/${item.runs?.get(0)?.wickets}"
                holder.team1BallsTv.text = "Overs(${item.runs?.get(1)?.overs})"
                holder.team2BallsTv.text = "Overs(${item.runs?.get(0)?.overs})"
            }
        } catch (e: Exception) {
            Log.e(
                TAG,
                "onBindViewHolder: \nitem: ${item.runs}\nerror: $e"
            )
        }
        holder.cardView.setOnClickListener{
            try {
                val action =
                    RecentFragmentDirections.actionRecentFragmentToMatchDetailsFragment(
                        item.localteam.name,
                        item.visitorteam.name,
                        item.localteam.image_path,
                        item.visitorteam.image_path,
                        item.runs?.get(0)?.score!!,
                        item.runs?.get(1)?.score!!,
                        item.runs?.get(0)?.wickets!!,
                        item.runs?.get(1)?.wickets!!,
                        item.runs?.get(0)?.overs!!.toFloat(),
                        item.runs?.get(1)?.overs!!.toFloat(),
                        item.note!!,
                        item.batting?.toTypedArray(),
                        item.bowling?.toTypedArray(),
                        item.lineup?.toTypedArray()
                    )
                findNavController(holder.itemView).navigate(action)
            } catch (e: Exception) {
                Log.e(TAG, "onBindViewHolder: error: $e")
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}