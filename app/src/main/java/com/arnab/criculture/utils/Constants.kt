package com.arnab.criculture.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class Constants {
    init {

    }

    companion object {
        val date = LocalDate.now()
        val date1 = date.plusDays(7)
        val dateFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val plusSevenDays = date1.format(dateFormater)
        const val BASE_URL = "https://cricket.sportmonks.com/api/v2.0/"
        const val API_TOKEN = "hg1DScD5eIMTkHGuBR0yW8nd7MubIt5UohYrAbhLycOJX3bk9a3TY9X889lk"
        const val ALL_TEAM_QUERY =
            "teams?api_token=${API_TOKEN}"
        const val LIVE_SCORE_QUERY = "livescores?api_token=${API_TOKEN}"

        const val UPCOMING_MATCHES_QUERY =
            "fixtures?filter[starts_between]=2023-02-17,2023-02-28&include=visitorteam,localteam,lineup&api_token=${API_TOKEN}"
        const val RECENT_MATCHES_QUERY =
            "fixtures?filter[starts_between]=2023-02-1,2023-02-24&include=visitorteam,localteam,lineup,venue,runs&api_token=${API_TOKEN}"
        const val TEST_RANKING_MEN_QUERY =
            "team-rankings?filter[type]=TEST&filter[gender]=men&api_token=${API_TOKEN}"
        const val ODI_RANKING_MEN_QUERY =
            "team-rankings?filter[type]=ODI&filter[gender]=men&api_token=${API_TOKEN}"
        const val T20_RANKING_MEN_QUERY =
            "team-rankings?filter[type]=T20&filter[gender]=men&api_token=${API_TOKEN}"
        const val ODI_RANKING_WOMEN_QUERY =
            "team-rankings?filter[type]=ODI&filter[gender]=women&api_token=${API_TOKEN}"
        const val T20_RANKING_WOMEN_QUERY =
            "team-rankings?filter[type]=T20&filter[gender]=women&api_token=${API_TOKEN}"

    }
}