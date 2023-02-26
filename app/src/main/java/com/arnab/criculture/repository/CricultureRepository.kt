package com.arnab.criculture.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.arnab.criculture.db.dao.ICricultureDao
import com.arnab.criculture.models.ranking.Ranking
import com.arnab.criculture.models.fixtures.FixtureWithLineUpandTeams
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.network.SportMonksApi

class CricultureRepository(private val cricultureDao: ICricultureDao) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getAllTeamsFromApi(): Team? {
        var team: Team? = null
        try {
            team = SportMonksApi.retrofitService.getAllTeams()
        } catch (e: Exception) {
            Log.e("TEST", "getAllTeamsFromApi: $e")
        }
        return team
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getUpcomingMatchesFromApi(): FixtureWithLineUpandTeams? {
        var upcomingMatches: FixtureWithLineUpandTeams? = null
        try {
            upcomingMatches = SportMonksApi.retrofitService.getUpcomingMatches()
        } catch (e: Exception) {
            Log.e("TEST", "getUpcomingMatchesFromApi: $e")
        }
        return upcomingMatches
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getRecentMatchesFromApi(): FixtureWithLineUpandTeams? {
        var upcomingMatches: FixtureWithLineUpandTeams? = null
        try {
            upcomingMatches = SportMonksApi.retrofitService.getRecentMatches()
        } catch (e: Exception) {
            Log.e("TEST", "getRecentMatchesFromApi: $e")
        }
        return upcomingMatches
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTestRankingMenFromApi(): Ranking {
        return SportMonksApi.retrofitService.getTestRankingMen()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getOdiRankingMenFromApi(): Ranking {
        return SportMonksApi.retrofitService.getOdiRankingMen()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getT20RankingMenFromApi(): Ranking {
        return SportMonksApi.retrofitService.getT20RankingMen()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getOdiRankingWomenFromApi(): Ranking {
        return SportMonksApi.retrofitService.getOdiRankingWomen()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getT20RankingWomenFromApi(): Ranking {
        return SportMonksApi.retrofitService.getT20RankingWomen()
    }

    suspend fun addTeam(teamData: TeamData) {
        cricultureDao.addTeam(teamData)
    }

    suspend fun getTeamById(id: Int): TeamData {
        return cricultureDao.getTeamById(id)
    }
}