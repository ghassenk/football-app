package com.gk.app.footballapp.presenter

import android.util.Log
import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.BuildConfig
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.search.TeamListItem
import com.gk.app.footballapp.view.search.TeamSearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamSearchPresenterImpl(
    override val view: TeamSearchView,
    private val mainView: MainView,
    private val teamGateway: TeamGateway,
) : TeamSearchPresenter {

    private val logTag = javaClass.simpleName

    override var isViewDestroyed = false
    private var autoCompleteList = ArrayList<String>()

    override fun loadAutoCompleteList() {
        if (autoCompleteList.isEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val leagues = teamGateway.searchAllSoccerLeagues()
                    if (!isViewDestroyed) {
                        leagues?.let {
                            it.forEach { autoCompleteList.add(it.name) }
                            view.updateAutocompleteList(autoCompleteList)
                        } ?: kotlin.run {
                            Log.e(logTag, "Failed to get leagues")
                        }
                    }
                } catch (e: Exception) {
                    Log.e(logTag, "Failed to get leagues", e)
                }
            }
        }
    }


    override fun onSearchClicked(keyword: String) {
        performSearch(keyword)
    }

    override fun onTeamListItemClicked(teamItem: TeamListItem) {
        mainView.showTeamDetailView(teamItem)
    }

    private fun performSearch(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                view.hideKeyboard()
                view.showProgress()
                val results = teamGateway.searchTeamsByLeagueName(keyword)
                if (BuildConfig.DEBUG) {
                    Log.i(logTag, "results=$results")
                }
                if (!isViewDestroyed) {
                    val teamListItems = ArrayList<TeamListItem>()
                    if (results != null && results.isNotEmpty()) {
                        results.forEach { teamListItems.add(TeamListItem(it.name, it.badgeUrl)) }
                        view.hideProgress()
                        view.hideError()
                        view.updateSearchListItems(teamListItems)
                    } else {
                        view.hideProgress()
                        view.showError("No teams found!")
                    }
                }
            } catch (e: Exception) {
                Log.e(logTag, "Search failed:", e)
                view.hideProgress()
                view.showError(e.message ?: "Search failed!")
            }
        }
    }

}