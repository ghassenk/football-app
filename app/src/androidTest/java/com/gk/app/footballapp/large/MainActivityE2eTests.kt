package com.gk.app.footballapp.large

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
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

    // We use content descriptions to check images and real api content.
    // (for frequently changing API content we would probably use a mock api)
    private val expectedSearchResultDescriptions = arrayListOf(
        "Angers", "Bordeaux", "Brest", "Clermont Foot", "Lens", "Lille", "Lorient", "Lyon",
        "Marseille", "Metz", "Monaco", "Montpellier", "Nantes", "Nice", "Paris SG", "Rennes",
        "St Etienne", "Stade de Reims", "Strasbourg", "Troyes",
    )
    private val testFakeLeagueName = "bla bla"// we assume that this league does not exist
    private val testLeagueName = "French ligue 1"
    private val testClickTeamName = "Paris SG"
    private val testClickPosition = 14

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

        // Given - A resumed MainActivity
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        assert(activityScenario.state == Lifecycle.State.RESUMED)

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

        // Given - A resumed MainActivity already showing a list of teams as a search result
        activityScenario.moveToState(Lifecycle.State.CREATED)
        activityScenario.moveToState(Lifecycle.State.RESUMED)
        assert(activityScenario.state == Lifecycle.State.RESUMED)
        val list = Espresso.onView(withId(R.id.search_fragment_recycler))

        Espresso.onView(withId(R.id.search_fragment_edit_text))
            .perform(replaceText(testLeagueName))
            .perform(pressImeActionButton())

        Thread.sleep(2000)

        // When - I click on an item
        list.perform(
            RecyclerViewActions.scrollToPosition<TeamItemRecyclerViewAdapter.ViewHolder>(testClickPosition)
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