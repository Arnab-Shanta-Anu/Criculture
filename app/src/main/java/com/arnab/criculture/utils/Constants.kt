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
        const val API_TOKEN = "hg1DScD5eIMTkHGuBR0yW8nd7MubIt5UohYrAbhLycOJX3bk9a3TY9X889lk"
        const val ALL_TEAM_QUERY =
            "teams?api_token=${API_TOKEN}"
        const val LIVE_SCORE_QUERY = "livescores?api_token=${API_TOKEN}"
        const val UPCOMING_MATCHES_QUERY =
            "fixtures?filter[starts_between]=2023-02-14,2023-02-28&include=visitorteam,localteam,lineup" +
                    "&api_token=${API_TOKEN}"
    }
}