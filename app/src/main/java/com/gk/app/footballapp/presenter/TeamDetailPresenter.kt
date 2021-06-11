package com.gk.app.footballapp.presenter

import com.gk.app.footballapp.view.BasePresenter
import com.gk.app.footballapp.view.detail.TeamDetailView

interface TeamDetailPresenter : BasePresenter {
    val view: TeamDetailView

    fun getTeamDetails(teamName: String)
}