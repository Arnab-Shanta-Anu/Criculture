package com.arnab.criculture.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arnab.criculture.db.CricultureDatabase
import com.arnab.criculture.models.fixtures.UpcomingMatch
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.repository.CricultureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

class CricultureViewModel(application: Application) : AndroidViewModel(application) {
    private val _allTeams = MutableLiveData<Team>()
    val allTeams = _allTeams

    private val _upcomingMatches = MutableLiveData<UpcomingMatch>()
    val upcomingMatches = _upcomingMatches

    private val _team = MutableLiveData<TeamData?>()

    val cricultureRepository: CricultureRepository

    init {
        val cricultureDao = CricultureDatabase.getDatabaseInstance(application).cricultureDao()
        cricultureRepository = CricultureRepository(cricultureDao)
        getAllTeams()
        getUpcomingMatches()
    }

    private fun getUpcomingMatches() {
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMatches.postValue(cricultureRepository.getUpcomingMatchesFromApi())
        }
        upcomingMatches.value?.data?.forEach {
            Log.d("TEST", "getUpcomingMatches: $it")
        }
    }

    private fun getAllTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allTeams.postValue(cricultureRepository.getAllTeamsFromApi())
                Log.d("TEST", "getAllPlayers: ${allTeams.value?.data?.size}")
                allTeams.value?.data?.forEach {
//                Log.d("TEST", "getAllPlayers: ${it.name}\n\n")
                    addTeam(it)
                }
            } catch (e: CancellationException) {
                Log.e("TEST", "getAllTeams: Error: $e")
            }
        }
    }

    /*fun getTeamByIdFromApi(id: Int): TeamData? {
        viewModelScope.launch(Dispatchers.IO) {
            _team.postValue(cricultureRepository.getTeamByIdFromApi(id))
        }
        return _team.value
    }*/

    private fun addTeam(teamData: TeamData) {
        viewModelScope.launch {
            try {
                cricultureRepository.addTeam(teamData)
            } catch (e: CancellationException) {
                Log.e("TEST", "addTeam: Error: $e")
            }
        }
    }

    fun getTeamById(id: Int): TeamData? {
        viewModelScope.launch(Dispatchers.IO) {
            _team.postValue(cricultureRepository.getTeamById(id))
        }
        return _team.value
    }
}