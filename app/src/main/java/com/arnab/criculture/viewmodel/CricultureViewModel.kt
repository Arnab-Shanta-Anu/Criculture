package com.arnab.criculture.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arnab.criculture.db.CricultureDatabase
import com.arnab.criculture.models.fixtures.FixtureWithLineUpandTeams
import com.arnab.criculture.models.fixtures.Lineup
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.repository.CricultureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

private const val TAG = "tag"
@RequiresApi(Build.VERSION_CODES.O)
class CricultureViewModel(application: Application) : AndroidViewModel(application) {
    private val _allTeams = MutableLiveData<Team>()
    val allTeams = _allTeams

    private val _upcomingMatches = MutableLiveData<FixtureWithLineUpandTeams>()
    val upcomingMatches = _upcomingMatches

    private val cricultureRepository: CricultureRepository

    init {
        Log.d(TAG, "viewmodel init called")
        val cricultureDao = CricultureDatabase.getDatabaseInstance(application).cricultureDao()
        cricultureRepository = CricultureRepository(cricultureDao)
        getUpcomingMatches()
        getAllTeams()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUpcomingMatches() {
        Log.d("TEST", "getUpcomingMatches: Executing getUpcomingMatches")
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMatches.postValue(cricultureRepository.getUpcomingMatchesFromApi())
            delay(1000)
            Log.d(TAG, "getUpcomingMatches: Upcoming Matches: ${_upcomingMatches.value?.data?.size}")
        }
        upcomingMatches.value?.data?.get(0)?.lineup?.forEach {
            Log.d("TEST", "getUpcomingMatches: $it")
        }
    }

    private fun getAllTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allTeams.postValue(cricultureRepository.getAllTeamsFromApi())
                Log.d("TEST", "getAllTeams: ${allTeams.value?.data?.size}")
                allTeams.value?.data?.forEach {
//                Log.d("TEST", "getAllPlayers: ${it.name}\n\n")
                    addTeam(it)
                }
            } catch (e: CancellationException) {
                Log.e("TEST", "getAllTeams: Error: $e")
            }
        }
    }

    private fun addTeam(teamData: TeamData) {
        viewModelScope.launch {
            try {
                cricultureRepository.addTeam(teamData)
            } catch (e: CancellationException) {
                Log.e("TEST", "addTeam: Error: $e")
            }
        }
    }
}