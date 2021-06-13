package com.gk.app.footballapp.large

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gk.app.footballapp.R
import com.gk.app.footballapp.TestData
import com.gk.app.footballapp.TestData.expectedSearchResultDescriptions
import com.gk.app.footballapp.TestData.expectedTeamDescription
import com.gk.app.footballapp.TestData.testClickPosition
import com.gk.app.footballapp.TestData.testClickTeamName
import com.gk.app.footballapp.TestData.testFakeLeagueName
import com.gk.app.footballapp.TestData.testLeagueName
import com.gk.app.footballapp.view.MainActivity
import com.gk.app.footballapp.view.search.TeamItemRecyclerViewAdapter
import org.hamcrest.Matchers
import org.hamcrest.Matchers.startsWith
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityE2eTests {

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private var activity: MainActivity? = null

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity = it }
    }

    @Test
    fun searchLeague_ShowsCorrectResultList() {

        // Given - A MainActivity

        // When - I type league name in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(replaceText(testLeagueName))
            .perform(pressImeActionButton())

        Thread.sleep(2000)

        // Then - I get the correct list of France's teams
        val list = Espresso.onView(withId(R.id.search_fragment_recycler))
        for ((index, desc) in expectedSearchResultDescriptions.withIndex()) {
            list.perform(
                RecyclerViewActions.scrollToPosition<TeamItemRecyclerViewAdapter.ViewHolder>(index)
            )
            Espresso.onView(withContentDescription(desc))
        }
    }

    @Test
    fun searchFakeLeague_ShowsError() {

        // Given - A MainActivity

        // When - I type fake league name in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(replaceText(testFakeLeagueName))
            .perform(pressImeActionButton())

        Thread.sleep(2000)


        // Then - An error is shown with the correct message
        Espresso.onView(withText("No teams found!"))
    }

    @Test
    fun clickOnTeamListItem_ShowsCorrectResult() {

        // Given - A  MainActivity showing some search results
        val list = Espresso.onView(withId(R.id.search_fragment_recycler))

        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(replaceText(testLeagueName))
            .perform(pressImeActionButton())

        Thread.sleep(2000)

        // When - I click on an item
        list.perform(
            RecyclerViewActions.scrollToPosition<TeamItemRecyclerViewAdapter.ViewHolder>(
                testClickPosition
            )
        )
        Espresso.onView(withContentDescription(testClickTeamName)).perform(click())

        Thread.sleep(1000)

        // Then - The details of the correct team are shown
        Espresso.onView(withId(R.id.detail_fragment_layout)).check(
            matches(isDisplayed())
        )

        Espresso.onView(withId(R.id.detail_fragment_description)).check(
            matches(withText(startsWith(expectedTeamDescription)))
        )
    }

    @Test
    fun searchLeague_ShowsCorrectAutoCompleteList() {

        // Given - A MainActivity

        // When - I type league name in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(clearText())
            .perform(typeText(TestData.testAutoCompleteKeyWord))

        Thread.sleep(2000)

        // Then - I get the correct list of Leagues
        for ((index, word) in TestData.expectedAutoCompleteWords.withIndex()) {
            Espresso.onView(withText(word))
                .inRoot(RootMatchers.withDecorView(Matchers.not(Matchers.`is`(activity!!.window.decorView))))
                .check(matches(isDisplayed()))
        }
    }

}