package com.gk.app.footballapp.presenter

import com.gk.app.footballapp.view.search.TeamSearchView

interface TeamSearchPresenter {
    val view: TeamSearchView

    fun onSearchTextUpdated(newText:String)
    fun onSearchClicked()

}