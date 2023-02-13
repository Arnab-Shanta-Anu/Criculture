package com.arnab.criculture.repository

import android.util.Log
import com.arnab.criculture.db.dao.ICricultureDao
import com.arnab.criculture.models.fixtures.UpcomingMatch
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.network.SportMonksApi

class CricultureRepository(private val cricultureDao: ICricultureDao) {
    suspend fun getAllTeamsFromApi(): Team? {
        var team: Team? = null
        try {
            team = SportMonksApi.retrofitService.getAllTeams()
        } catch (e: Exception) {
            Log.e("TEST", "getAllTeamsFromApi: $e")
        }
        return team
    }

    suspend fun getUpcomingMatchesFromApi(): UpcomingMatch? {
        var upcomingMatches: UpcomingMatch? = null
        try {
            upcomingMatches = SportMonksApi.retrofitService.getUpcomingMatches()
        } catch (e: Exception) {
            Log.e("TEST", "getUpcomingMatchesFromApi: $e")
        }
        return upcomingMatches
    }

    suspend fun getTeamByIdFromApi(id: Int): Team {
        return SportMonksApi.retrofitService.getTeamByID(id)
    }

    suspend fun addTeam(teamData: TeamData) {
        cricultureDao.addTeam(teamData)
    }

    suspend fun getTeamById(id: Int): TeamData{
        return cricultureDao.getTeamById(id)
    }
}