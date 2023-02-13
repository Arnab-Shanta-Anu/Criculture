package com.arnab.criculture.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.arnab.criculture.models.fixtures.UpcomingMatch
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface SportMonksApiService {
    @RequiresApi(Build.VERSION_CODES.O)
    @GET(Constants.ALL_TEAM_QUERY)
    suspend fun getAllTeams() : Team
    @RequiresApi(Build.VERSION_CODES.O)
    @GET(Constants.UPCOMING_MATCHES_QUERY)
    suspend fun getUpcomingMatches(): UpcomingMatch
    @GET("teams/{id}&api_token=${Constants.API_TOKEN}")
    suspend fun getTeamByID(@Path("id") id: Int): Team
}

object SportMonksApi{
    val retrofitService: SportMonksApiService by lazy { retrofit.create(SportMonksApiService::class.java) }
}