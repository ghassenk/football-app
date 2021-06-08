package com.gk.app.football.domain.gateway

import com.gk.app.football.domain.entity.Team

interface TeamGateway {

    fun searchTeamsByLeagueName(name: String): List<Team>

    fun getLeagueNamesFromPrefix(namePrefix: String): List<String>

}