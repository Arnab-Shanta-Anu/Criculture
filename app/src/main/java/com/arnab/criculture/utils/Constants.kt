package com.arnab.criculture.utils

class Constants {
    companion object{
        const val BASE_URL="https://cricket.sportmonks.com/api/v2.0/"
        private const val API_TOKEN="hg1DScD5eIMTkHGuBR0yW8nd7MubIt5UohYrAbhLycOJX3bk9a3TY9X889lk"
        const val ALL_PLAYER_QUERY="${BASE_URL}players?api_token=${API_TOKEN}"
        const val ALL_TEAM_QUERY="${BASE_URL}teams?filter[country_id]=190324&api_token=${API_TOKEN}"
        const val ALL_FIXTURE_QUERY="${BASE_URL}fixtures?api_token=${API_TOKEN}"
        const val LIVE_SCORE_QUERY="${BASE_URL}livescores?api_token=${API_TOKEN}"
    }
}