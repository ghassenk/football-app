package com.gk.app.footballapp.presenter

import android.util.Log
import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.BuildConfig
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.detail.TeamDetailView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamDetailPresenterImpl(
    override val view: TeamDetailView,
    private val mainView: MainView,
    private val teamGateway: TeamGateway,
) : TeamDetailPresenter {

    private val logTag = javaClass.simpleName

    override var isViewDestroyed = false

    override fun getTeamDetails(teamName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                view.showProgress()
                val results = teamGateway.searchTeamDetails(teamName)
                if (BuildConfig.DEBUG) {
                    Log.i(logTag, "results=$results")
                }
                if (!isViewDestroyed) {
                    if (results != null && results.isNotEmpty()) {
                        val firstTeam = results[0]
                        view.hideProgress()
                        view.hideError()
                        view.updateViews(
                            firstTeam.bannerUrl,
                            firstTeam.name,
                            firstTeam.country,
                            firstTeam.league,
                            firstTeam.description,
                        )
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