package com.gk.app.football.domain.gateway

import com.gk.app.football.domain.entity.League
import com.gk.app.football.domain.entity.Team

interface TeamGateway {

    suspend fun searchTeamsByLeagueName(leagueName: String): List<Team>?

    suspend fun searchTeamDetails(teamName: String): List<Team>?

    suspend fun searchAllLeagues(): List<League>?

    fun getLeagueNames(): List<String>?
}