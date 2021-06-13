package com.gk.app.footballapp.medium

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.gk.app.footballapp.R
import com.gk.app.footballapp.TestData.expectedTeamDescription
import com.gk.app.footballapp.TestData.testCountryName
import com.gk.app.footballapp.TestData.testFakeTeamName
import com.gk.app.footballapp.TestData.testLeagueName
import com.gk.app.footballapp.TestData.testRealTeamName
import com.gk.app.footballapp.launchFragmentInHiltMainContainer
import com.gk.app.footballapp.view.detail.TeamDetailFragment
import com.gk.app.footballapp.view.detail.TeamDetailFragment.Companion.ARG_TEAM_NAME
import org.hamcrest.Matchers.startsWith
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests checking that integration between TeamDetailFragment and its presenter are working correctly
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class TeamDetailFragmentIntegrationTests {

    @Before
    fun setUp() {

    }

    @Test
    fun realTeamName_ShowsCorrectContents() {

        // Given - A TeamDetailFragment launched with a real team name
        val fragmentArgs = bundleOf(ARG_TEAM_NAME to testRealTeamName)
        launchFragment(fragmentArgs)

        Thread.sleep(2000)

        // Then - I get the correct content
        Espresso.onView(withId(R.id.detail_fragment_team_name)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(testRealTeamName))
        )
        Espresso.onView(withId(R.id.detail_fragment_banner_image)).check(
            matches(isDisplayed())
        )
        // TODO improve image testing with url content description

        Espresso.onView(withId(R.id.detail_fragment_country_text)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(testCountryName))
        )
        Espresso.onView(withId(R.id.detail_fragment_league_text)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(testLeagueName))
        )
        Espresso.onView(withId(R.id.detail_fragment_description)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(startsWith(expectedTeamDescription)))
        )

    }

    @Test
    fun fakeTeamName_ShowsError() {

        // Given - A TeamDetailFragment launched with a real team name
        val fragmentArgs = bundleOf(ARG_TEAM_NAME to testFakeTeamName)
        launchFragment(fragmentArgs)

        Thread.sleep(2000)

        // Then - I get an error message
        Espresso.onView(withId(R.id.detail_fragment_error_text)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(startsWith("No teams found!")))
        )
    }

    private fun launchFragment(fragmentArgs: Bundle) {
        launchFragmentInHiltMainContainer<TeamDetailFragment>(fragmentArgs = fragmentArgs,
            R.style.Theme_FootballApp) {}
    }
}