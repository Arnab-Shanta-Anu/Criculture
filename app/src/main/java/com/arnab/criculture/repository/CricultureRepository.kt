package com.arnab.criculture.repository

import com.arnab.criculture.db.dao.ICricultureDao
import com.arnab.criculture.models.teams.TeamData

class CricultureRepository(private val cricultureDao: ICricultureDao) {
    suspend fun addTeam(teamData: TeamData) {
        cricultureDao.addTeam(teamData)
    }
}