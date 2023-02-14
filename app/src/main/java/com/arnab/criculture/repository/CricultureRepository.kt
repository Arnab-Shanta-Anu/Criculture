package com.arnab.criculture.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.arnab.criculture.db.dao.ICricultureDao
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
    suspend fun getTeamByIdFromApi(id: Int): Team {
        return SportMonksApi.retrofitService.getTeamByID(id)
    }

    suspend fun addTeam(teamData: TeamData) {
        cricultureDao.addTeam(teamData)
    }

    suspend fun getTeamById(id: Int): TeamData {
        return cricultureDao.getTeamById(id)
    }
}