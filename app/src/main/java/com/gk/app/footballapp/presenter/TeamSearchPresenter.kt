package com.gk.app.footballapp.presenter

import com.gk.app.footballapp.view.BasePresenter
import com.gk.app.footballapp.view.search.TeamListItem
import com.gk.app.footballapp.view.search.TeamSearchView

interface TeamSearchPresenter : BasePresenter {
    val view: TeamSearchView

    fun loadAutoCompleteList()
    fun onSearchClicked(keyword: String)
    fun onTeamListItemClicked(teamItem: TeamListItem)

}