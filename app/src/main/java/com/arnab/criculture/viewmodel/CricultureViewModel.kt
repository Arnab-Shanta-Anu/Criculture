package com.arnab.criculture.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arnab.criculture.db.CricultureDatabase
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.models.teams.TeamData
import com.arnab.criculture.network.SportMonksApi
import com.arnab.criculture.repository.CricultureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CricultureViewModel(application: Application) : AndroidViewModel(application) {
    private val _allTeams = MutableLiveData<Team>()
    val allTeams = _allTeams
    val cricultureRepository: CricultureRepository

    init {
        val cricultureDao = CricultureDatabase.getDatabaseInstance(application).cricultureDao()
        cricultureRepository = CricultureRepository(cricultureDao)
        getAllPlayers()
    }

    private fun getAllPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            _allTeams.postValue(cricultureRepository.getAllTeams())
            Log.d("TEST", "getAllPlayers: ${allTeams.value?.data?.size}")
            allTeams.value?.data?.forEach{
                Log.d("TEST", "getAllPlayers: ${it.name}\n\n")
                addTeam(it)
            }
        }
    }
    private fun addTeam(teamData: TeamData){
        viewModelScope.launch {
            cricultureRepository.addTeam(teamData)
        }
    }
}