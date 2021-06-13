package com.gk.app.footballapp

import android.widget.ProgressBar
import android.widget.TextView
import com.gk.app.football.domain.entity.League
import com.gk.app.football.domain.entity.Team
import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.detail.TeamDetailView
import com.gk.app.footballapp.view.search.TeamListItem
import org.mockito.Mockito.mock

object TestDoubles {

    class SpyTeamDetailView : TeamDetailView {
        var bannerUrl: String? = null
        var teamName: String? = null
        var countryName: String? = null
        var leagueName: String? = null
        var description: String? = null

        override fun updateViews(
            bannerUrl: String?,
            teamName: String?,
            countryName: String?,
            leagueName: String?,
            description: String?
        ) {
            this.bannerUrl = bannerUrl
            this.teamName = teamName
            this.countryName = countryName
            this.leagueName = leagueName
            this.description = description
        }

        override var errorText: TextView = mock(TextView::class.java)
        override var progressBar: ProgressBar = mock(ProgressBar::class.java)
    }

    class SpyMainView : MainView {

        lateinit var team: TeamListItem

        override fun showTeamDetailView(team: TeamListItem) {
            this.team = team
        }
    }

    class SpyTeamGateway : TeamGateway {
        lateinit var lastSearchedLeagueName: String
        lateinit var lastSearchedTeamName: String

        override suspend fun searchTeamsByLeagueName(leagueName: String): List<Team>? {
            this.lastSearchedLeagueName = leagueName
            return null
        }

        override suspend fun searchTeamDetails(teamName: String): List<Team>? {
            this.lastSearchedTeamName = teamName
            return null
        }

        override suspend fun searchAllSoccerLeagues(): List<League>? {
            return null
        }
    }

    class MockDetailTeamGateway : TeamGateway {
        val mockTeam = MockTeam()

        override suspend fun searchTeamsByLeagueName(leagueName: String): List<Team>? {
            return null
        }

        override suspend fun searchTeamDetails(teamName: String): List<Team>? {
            val result = ArrayList<Team>()
            result.add(mockTeam)
            return result
        }

        override suspend fun searchAllSoccerLeagues(): List<League>? {
            return null
        }

    }

    class MockTeam : Team {
        override var name = "mock_name"
        override var bannerUrl: String? = "mock_bannerUrl"
        override var badgeUrl: String? = "mock_badgeUrl"
        override var country: String? = "mock_country"
        override var league: String? = "mock_league"
        override var description: String? = "mock_description"
    }
}