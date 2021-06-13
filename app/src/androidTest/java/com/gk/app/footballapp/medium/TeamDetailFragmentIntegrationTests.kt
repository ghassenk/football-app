package com.gk.app.footballapp.medium

import android.app.Activity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.gk.app.footballapp.R
import com.gk.app.footballapp.launchFragmentInHiltContainer
import com.gk.app.footballapp.view.MainActivity
import com.gk.app.footballapp.view.detail.TeamDetailFragment
import com.gk.app.footballapp.view.detail.TeamDetailFragment.Companion.ARG_TEAM_NAME
import com.gk.app.footballapp.view.search.TeamItemRecyclerViewAdapter
import org.hamcrest.Matchers
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

    private lateinit var fragmentScenario: FragmentScenario<TeamDetailFragment>
    private var fragment: Fragment? = null

    private val testRealTeamName = "Paris SG"
    private val testFakeTeamName = "bla bla"

    @Before
    fun setUp() {
//        val fragmentArgs = bundleOf(ARG_TEAM_NAME to testRealTeamName)
//        fragmentScenario = launchFragmentInContainer(fragmentArgs)
//        fragmentScenario.onFragment {
//            activity = it.activity
//            fragment = it
//        }
    }

    @Test
    fun realTeamName_ShowsCorrectContents() {

        // Given - A resumed TeamDetailFragment launched with a real team name
        val fragmentArgs = bundleOf(ARG_TEAM_NAME to testRealTeamName)
        launchFragment(fragmentArgs)

        // Then - I get the correct content

    }

    @Test
    fun fakeTeamName_ShowsError() {

        // Given - A resumed TeamDetailFragment launched with a real team name
        val fragmentArgs = bundleOf(ARG_TEAM_NAME to testFakeTeamName)
        launchFragment(fragmentArgs)


        // Then - I get an error message
        Espresso.onView(withId(R.id.detail_fragment_error_text)).check(
            matches(isDisplayed())
        ).check(
            matches(withText(startsWith("No teams found!")))
        )
    }

    private fun launchFragment(fragmentArgs: Bundle) {
        launchFragmentInHiltContainer<TeamDetailFragment>(fragmentArgs = fragmentArgs,
            R.style.Theme_FootballApp) {}
    }
}