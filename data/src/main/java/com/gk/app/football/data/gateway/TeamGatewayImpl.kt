package com.gk.app.football.data.gateway

import com.gk.app.football.domain.entity.Team
import com.gk.app.football.domain.gateway.TeamGateway

class TeamGatewayImpl : TeamGateway {

    override fun searchTeamsByLeagueName(name: String): List<Team> {
        TODO("Not yet implemented")
    }

    override fun getLeagueNamesFromPrefix(namePrefix: String): List<String> {
        TODO("Not yet implemented")
    }
}