package com.gk.app.footballapp.view

import com.gk.app.footballapp.view.search.TeamListItem

interface MainView {
    fun showTeamDetailView(team: TeamListItem)
}