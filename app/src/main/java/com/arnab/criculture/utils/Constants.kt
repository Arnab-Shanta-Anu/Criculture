package com.arnab.criculture.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class Constants {
    init {
        val format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        Constants.date = LocalDateTime.now().format(format)
    }

    companion object {
        var date: String = ""

        const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
        private const val API_TOKEN = "hg1DScD5eIMTkHGuBR0yW8nd7MubIt5UohYrAbhLycOJX3bk9a3TY9X889lk"
        const val ALL_PLAYER_QUERY = "${BASE_URL}players?api_token=${API_TOKEN}"
        const val ALL_TEAM_QUERY =
            "${BASE_URL}teams?filter[country_id]=190324&api_token=${API_TOKEN}"
        const val ALL_FIXTURE_QUERY = "${BASE_URL}fixtures?api_token=${API_TOKEN}"
        const val LIVE_SCORE_QUERY = "${BASE_URL}livescores?api_token=${API_TOKEN}"
        const val UPCOMING_MATCHES_QUERY =
            "fixtures?filter[starts_between]=2023-02-01,2023-02-28&api_token=${API_TOKEN}"
    }
}