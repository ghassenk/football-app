package com.gk.app.footballapp.small

import com.gk.app.footballapp.TestData.testRealTeamName
import com.gk.app.footballapp.TestDoubles.MockDetailTeamGateway
import com.gk.app.footballapp.TestDoubles.SpyMainView
import com.gk.app.footballapp.TestDoubles.SpyTeamDetailView
import com.gk.app.footballapp.TestDoubles.SpyTeamGateway
import com.gk.app.footballapp.presenter.TeamDetailPresenterImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TeamDetailPresenterUnitTests {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getTeamDetails_CallsGatewayCorrectly() {
        val gateway = SpyTeamGateway()
        val presenter = TeamDetailPresenterImpl(
            SpyTeamDetailView(),
            SpyMainView(),
            gateway,
        )

        // Using blocking run to simulate real main thread
        runBlocking {
            presenter.getTeamDetails(testRealTeamName)
        }

        Assert.assertEquals(gateway.lastSearchedTeamName, testRealTeamName)
    }


    @Test
    fun getTeamDetails_CallsTeamDetailViewCorrectly() {
        val teamDetailView = SpyTeamDetailView()
        val mockDetailTeamGateway = MockDetailTeamGateway()
        val presenter = TeamDetailPresenterImpl(
            teamDetailView,
            SpyMainView(),
            mockDetailTeamGateway,
        )

        runBlocking {
            presenter.getTeamDetails(testRealTeamName)
        }

        Assert.assertEquals(
            mockDetailTeamGateway.mockTeam.name,
            teamDetailView.teamName
        )
        Assert.assertEquals(
            mockDetailTeamGateway.mockTeam.bannerUrl,
            teamDetailView.bannerUrl
        )
        Assert.assertEquals(
            mockDetailTeamGateway.mockTeam.country,
            teamDetailView.countryName
        )
        Assert.assertEquals(
            mockDetailTeamGateway.mockTeam.league,
            teamDetailView.leagueName
        )
        Assert.assertEquals(
            mockDetailTeamGateway.mockTeam.description,
            teamDetailView.description
        )

    }
}