package com.arnab.criculture.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arnab.criculture.models.teams.TeamData
import com.google.android.material.circularreveal.CircularRevealHelper.Strategy

@Dao
interface ICricultureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTeam(teamData: TeamData)
    @Query("SELECT * FROM teams WHERE id=:id")
    suspend fun getTeamById(id: Int): TeamData
}