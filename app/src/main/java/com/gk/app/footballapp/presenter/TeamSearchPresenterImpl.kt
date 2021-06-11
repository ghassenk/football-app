package com.gk.app.footballapp.presenter

import android.util.Log
import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.search.TeamListItem
import com.gk.app.footballapp.view.search.TeamSearchView
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamSearchPresenterImpl(
    override val view: TeamSearchView,
    private val teamGateway: TeamGateway,
    private val mainView: MainView,
) : TeamSearchPresenter {

    private val logTag = javaClass.simpleName

    private var isViewDestroyed = false

    override fun onSearchTextUpdated(newText: String) {
        // do we need this?
    }

    override fun onSearchClicked(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                view.hideKeyboard()
                view.showProgress()
                val results = teamGateway.searchTeamsByLeagueName(keyword)
                if (!isViewDestroyed) {
                    val teamListItems = ArrayList<TeamListItem>()
                    results.forEach { teamListItems.add(TeamListItem(it.name, it.bannerUrl)) }
                    view.hideProgress()
                    view.hideError()
                    view.updateSearchListItems(teamListItems)
                }
            } catch (e: Exception) {
                Log.e(logTag, "Search failed:", e)
                view.hideProgress()
                view.showError(e.message ?: "Search failed!")
            }
        }
    }

    override fun onTeamListItemClicked(teamItem: TeamListItem) {
        mainView.showTeamDetailView(teamItem)
    }

    override fun onViewDestroyed() {
        isViewDestroyed = true
    }

}