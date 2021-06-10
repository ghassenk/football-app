package com.gk.app.footballapp.presenter

import com.gk.app.footballapp.view.detail.TeamDetailView

interface TeamDetailPresenter {
    val view: TeamDetailView

    fun getTeamDetails(team: String)
}