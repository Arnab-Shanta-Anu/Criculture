package com.arnab.criculture.viewmodel

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arnab.criculture.db.CricultureDatabase
import com.arnab.criculture.models.Ranking.Ranking
import com.arnab.criculture.models.fixtures.FixtureWithLineUpandTeams
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.repository.CricultureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

private const val TAG = "CricultureViewModel"

@RequiresApi(Build.VERSION_CODES.O)
class CricultureViewModel(application: Application) : AndroidViewModel(application) {
    private val _allTeams = MutableLiveData<Team>()
    val allTeams = _allTeams

    private val _upcomingMatches = MutableLiveData<FixtureWithLineUpandTeams>()
    val upcomingMatches = _upcomingMatches

    private val _recentMatches = MutableLiveData<FixtureWithLineUpandTeams>()
    val recentMatches = _recentMatches

    val _testRankingMen = MutableLiveData<Ranking>()
    val _odiRankingMen = MutableLiveData<Ranking>()
    val _t20RankingMen = MutableLiveData<Ranking>()

    val _testRankingWomen = MutableLiveData<Ranking>()
    val _odiRankingWomen = MutableLiveData<Ranking>()
    val _t20RankingWomen = MutableLiveData<Ranking>()

    private val cricultureRepository: CricultureRepository

    init {
        Log.d(TAG, "viewmodel init called")
        val cricultureDao = CricultureDatabase.getDatabaseInstance(application).cricultureDao()
        cricultureRepository = CricultureRepository(cricultureDao)
        getUpcomingMatches()
        getRecentMatches()
        getAllTeams()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUpcomingMatches() {
        Log.d(TAG, "getUpcomingMatches: Executing getUpcomingMatches")
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMatches.postValue(cricultureRepository.getUpcomingMatchesFromApi())
            delay(1000)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getRecentMatches() {
        Log.d(TAG, "getUpcomingMatches: Executing getUpcomingMatches")
        viewModelScope.launch(Dispatchers.IO) {
            _recentMatches.postValue(cricultureRepository.getRecentMatchesFromApi())
            delay(1000)
        }
    }

    private fun getAllTeams() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allTeams.postValue(cricultureRepository.getAllTeamsFromApi())
                Log.d(TAG, "getAllTeams: ${allTeams.value?.data?.size}")
                allTeams.value?.data?.forEach {
//                Log.d("TEST", "getAllPlayers: ${it.name}\n\n")
                    addTeam(it)
                }
            } catch (e: CancellationException) {
                Log.e(TAG, "getAllTeams: Error: $e")
            }
        }
    }

    fun getRanking() {
        viewModelScope.launch {
            getTestRankingMen()
            getOdiRankingMen()
            getT20RankingMen()
            getOdiRankingWomen()
            getT20RankingWomen()
        }
    }

    private suspend fun getT20RankingWomen() {
        _t20RankingWomen.postValue(cricultureRepository.getT20RankingWomenFromApi())
    }

    private suspend fun getOdiRankingWomen() {
        _odiRankingWomen.postValue(cricultureRepository.getOdiRankingWomenFromApi())
    }

    private suspend fun getT20RankingMen() {
        _t20RankingMen.postValue(cricultureRepository.getT20RankingMenFromApi())
    }

    private suspend fun getOdiRankingMen() {
        _odiRankingMen.postValue(cricultureRepository.getOdiRankingMenFromApi())
    }

    private suspend fun getTestRankingMen() {
        _testRankingMen.postValue(cricultureRepository.getTestRankingMenFromApi())
    }

    private fun addTeam(teamData: TeamData) {
        viewModelScope.launch {
            try {
                cricultureRepository.addTeam(teamData)
            } catch (e: CancellationException) {
                Log.e(TAG, "addTeam: Error: $e")
            }
        }
    }
}