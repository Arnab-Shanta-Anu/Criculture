package com.arnab.criculture.network

import com.arnab.criculture.models.fixtures.UpcomingMatch
import com.arnab.criculture.models.teams.Team
import com.arnab.criculture.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

interface SportMonksApiService {
    @GET(Constants.ALL_TEAM_QUERY)
    suspend fun getAllTeams() : Team
    @GET(Constants.UPCOMING_MATCHES_QUERY)
    suspend fun getUpcomingMatches(): UpcomingMatch
}

object SportMonksApi{
    val retrofitService: SportMonksApiService by lazy { retrofit.create(SportMonksApiService::class.java) }
}