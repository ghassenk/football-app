package com.gk.app.footballapp.medium

import android.os.Bundle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.gk.app.footballapp.R
import com.gk.app.footballapp.TestData
import com.gk.app.footballapp.TestData.testAutoCompleteKeyWord
import com.gk.app.footballapp.TestData.testLeagueName
import com.gk.app.footballapp.launchFragmentInHiltMainContainer
import com.gk.app.footballapp.view.search.TeamItemRecyclerViewAdapter
import com.gk.app.footballapp.view.search.TeamSearchFragment
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests checking that integration between TeamSearchFragment and its presenter are working correctly
 */
@MediumTest
@RunWith(AndroidJUnit4::class)
class TeamSearchFragmentIntegrationTests {

    @Before
    fun setUp() {
        launchFragment()
    }

    @Test
    fun searchLeague_ShowsCorrectResultList() {

        // Given - A TeamSearchFragment

        // When - I type league name in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(ViewActions.replaceText(testLeagueName))
            .perform(ViewActions.pressImeActionButton())

        Thread.sleep(2000)

        // Then - I get the correct list of France's teams
        val list = Espresso.onView(withId(R.id.search_fragment_recycler))
        for ((index, desc) in TestData.expectedSearchResultDescriptions.withIndex()) {
            list.perform(
                RecyclerViewActions.scrollToPosition<TeamItemRecyclerViewAdapter.ViewHolder>(index)
            )
            Espresso.onView(withContentDescription(desc))
        }
    }

    @Test
    fun searchFakeLeague_ShowsError() {

        // Given - A  TeamSearchFragment

        // When - I type fake league name in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(ViewActions.replaceText(TestData.testFakeLeagueName))
            .perform(ViewActions.pressImeActionButton())

        Thread.sleep(2000)


        // Then - An error is shown with the correct message
        Espresso.onView(withText("No teams found!"))
    }

    private fun launchFragment(fragmentArgs: Bundle? = null) {
        launchFragmentInHiltMainContainer<TeamSearchFragment>(
            fragmentArgs = fragmentArgs,
            R.style.Theme_FootballApp
        ) {}
    }
}