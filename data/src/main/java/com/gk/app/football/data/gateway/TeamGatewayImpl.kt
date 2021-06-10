package com.gk.app.football.data.gateway

import com.gk.app.football.data.internal.remote.ApiWebservice
import com.gk.app.football.domain.entity.League
import com.gk.app.football.domain.entity.Team
import com.gk.app.football.domain.gateway.TeamGateway
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamGatewayImpl(
    private val apiBaseUrl: String
) : TeamGateway {

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val webservice: ApiWebservice by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(apiBaseUrl)
            .build()
        retrofit.create(ApiWebservice::class.java)
    }

    private val leagueNames = ArrayList<String>()

    override suspend fun searchTeamsByLeagueName(leagueName: String): List<Team> {
        val queryMap = mapOf(leagueName to "l")
        val response = webservice.searchTeams(
            endpointPath = "search_all_teams.php",
            queryMap = queryMap,
        )

        return response.teams
    }

    override suspend fun searchTeamDetails(teamName: String): List<Team> {
        val queryMap = mapOf(teamName to "t")
        val response = webservice.searchTeams(
            endpointPath = "searchteams.php",
            queryMap = queryMap,
        )

        return response.teams
    }

    override suspend fun searchAllLeagues(): List<League> {
        val response = webservice.searchLeagues(
            endpointPath = "all_leagues.php",
            cacheControl = "max-age=600000"
        )

        // Add to memory cache
        response.leagues.forEach { leagueNames.add(it.name) }

        return response.leagues
    }

    override fun getLeagueNames(): List<String> {
        return leagueNames
    }


}