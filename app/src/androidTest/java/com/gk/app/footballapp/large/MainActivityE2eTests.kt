package com.gk.app.footballapp.large

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.gk.app.footballapp.R
import com.gk.app.footballapp.view.MainActivity
import com.gk.app.footballapp.view.search.TeamItemRecyclerViewAdapter
import org.hamcrest.Matchers.startsWith
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityE2eTests {

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private var activity: MainActivity? = null

    // We use content descriptions to check images (for frequently changing API content we would
    // probably use a mock api)
    private val expectedSearchResultDescriptions = arrayListOf(
        "Angers", "Bordeaux", "Brest", "Clermont Foot", "Lens", "Lille", "Lorient", "Lyon",
        "Marseille", "Metz", "Monaco", "Montpellier", "Nantes", "Nice", "Paris SG", "Rennes",
        "St Etienne", "Stade de Reims", "Strasbourg", "Troyes",
    )
    private val testLeagueName = "French league 1"
    private val testClickTeamName = "Paris SG"

    // For simplification we will use only the beginning of the description
    private val expectedTeamDescription =
        "Paris Saint-Germain Football Club, commonly referred to " +
                "as Paris Saint-Germain, Paris SG, or simply Paris or PSG, is a French professional " +
                "football club based in Paris. Founded in 1970,"

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity = it }
    }

    @Test
    fun searchLeague_ShowsCorrectResultList() {

        // Given - A resumed MainActivity
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        assert(activityScenario.state == Lifecycle.State.RESUMED)

        // When - I type "france" in the search bar
        Espresso.onView(withId(R.id.search_fragment_edit_text)).perform(
            replaceText(testLeagueName)
        )

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
    fun clickOnTeamListItem_ShowsCorrectResult() {

        // Given - A resumed MainActivity already showing a list of teams as a search result
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        assert(activityScenario.state == Lifecycle.State.RESUMED)
        Espresso.onView(withId(R.id.search_fragment_edit_text)).perform(
            replaceText(testLeagueName)
        )
        val list = Espresso.onView(withId(R.id.search_fragment_recycler)).check(
            matches(isDisplayed())
        )

        // When - I click on an item
        list.perform(
            RecyclerViewActions.scrollTo<TeamItemRecyclerViewAdapter.ViewHolder>(
                withContentDescription(testClickTeamName)
            )
        )
        Espresso.onView(withContentDescription(testClickTeamName)).perform(click())

        // Then - The details of the correct team are shown
        Espresso.onView(withId(R.id.detail_fragment_layout)).check(
            matches(isDisplayed())
        )

        Espresso.onView(withId(R.id.detail_fragment_description)).check(
            matches(withText(startsWith(expectedTeamDescription)))
        )

    }

    //@Test
    fun templateTest() {
        // Given -
        // When -
        // Then -
    }
}