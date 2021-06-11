package com.gk.app.footballapp.presenter

import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.detail.TeamDetailView

class TeamDetailPresenterImpl(
    override val view: TeamDetailView,
    private val teamGateway: TeamGateway,
    private val mainView: MainView,
) : TeamDetailPresenter {

    override fun getTeamDetails(team: String) {
        TODO("Not yet implemented")
    }
}