package com.gk.app.football.data.gateway

import com.gk.app.football.data.internal.remote.ApiWebservice
import com.gk.app.football.domain.entity.League
import com.gk.app.football.domain.entity.Team
import com.gk.app.football.domain.gateway.TeamGateway
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamGatewayImpl(
    private val apiBaseUrl: String,
    private val enableHttpLogging: Boolean,
) : TeamGateway {

    private val okHttpClient: OkHttpClient by lazy {
        if (enableHttpLogging) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    private val webservice: ApiWebservice by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(apiBaseUrl)
            .build()
        retrofit.create(ApiWebservice::class.java)
    }

    private var leagueNames : ArrayList<String>? = null

    override suspend fun searchTeamsByLeagueName(leagueName: String): List<Team>? {
        val queryMap = mapOf("l" to leagueName)
        val response = webservice.searchTeams(
            endpointPath = "search_all_teams.php",
            cacheControl = "max-age=3600", //refresh every hour
            queryMap = queryMap,
        )

        println("response=$response")

        return response.teams
    }

    override suspend fun searchTeamDetails(teamName: String): List<Team>? {
        val queryMap = mapOf("t" to teamName)
        val response = webservice.searchTeams(
            endpointPath = "searchteams.php",
            cacheControl = "max-age=3600", //refresh every hour
            queryMap = queryMap,
        )

        return response.teams
    }

    override suspend fun searchAllLeagues(): List<League> {
        val response = webservice.searchLeagues(
            endpointPath = "all_leagues.php",
            cacheControl = "max-age=600000" //refresh every week
        )

        // Add to memory cache
        leagueNames = ArrayList()
        response.leagues.forEach { leagueNames!!.add(it.name) }

        return response.leagues
    }

    override fun getLeagueNames(): List<String>? {
        return leagueNames
    }


}