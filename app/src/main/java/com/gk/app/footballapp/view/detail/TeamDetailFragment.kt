package com.gk.app.footballapp.view.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gk.app.footballapp.BuildConfig
import com.gk.app.footballapp.R
import com.gk.app.footballapp.presenter.TeamDetailPresenter
import com.gk.app.footballapp.view.image.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a team details.
 */
@AndroidEntryPoint
class TeamDetailFragment : Fragment(), TeamDetailView {

    private val logTag = javaClass.simpleName

    @Inject
    lateinit var teamDetailPresenter: TeamDetailPresenter
    override lateinit var errorText: TextView
    override lateinit var progressBar: ProgressBar

    lateinit var scrollView: View
    private lateinit var teamNameText: TextView
    private lateinit var bannerImage: ImageView
    private lateinit var countryNameText: TextView
    private lateinit var leagueNameText: TextView
    private lateinit var descriptionText: TextView

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_detail, container, false)
        errorText = view.findViewById(R.id.detail_fragment_error_text)
        progressBar = view.findViewById(R.id.detail_fragment_progress_bar)

        teamNameText = view.findViewById(R.id.detail_fragment_team_name)
        bannerImage = view.findViewById(R.id.detail_fragment_banner_image)
        countryNameText = view.findViewById(R.id.detail_fragment_country_text)
        leagueNameText = view.findViewById(R.id.detail_fragment_league_text)
        descriptionText = view.findViewById(R.id.detail_fragment_description)
        scrollView = view.findViewById(R.id.detail_fragment_scroll_view)

        val team = arguments?.getString(ARG_TEAM_NAME)
        team?.let {
            teamDetailPresenter.getTeamDetails(team)
        } ?: kotlin.run {
            throw IllegalArgumentException("No team argument found!")
        }

        return view
    }

    override fun showError(message: String) {
        scrollView.visibility = View.GONE

        super.showError(message)
    }

    override fun hideError() {
        scrollView.visibility = View.VISIBLE

        super.hideError()
    }

    override fun updateViews(
        bannerUrl: String?,
        teamName: String?,
        countryName: String?,
        leagueName: String?,
        description: String?
    ) {
        bannerUrl?.let { imageLoader.loadImage(bannerImage, it) }
        teamName?.let { teamNameText.text = it }
        countryName?.let { countryNameText.text = it }
        leagueName?.let { leagueNameText.text = it }
        description?.let { descriptionText.text = it }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        teamDetailPresenter.onViewDestroyed()
    }


    companion object {

        const val ARG_TEAM_NAME = "team-name"

        @JvmStatic
        fun newInstance(teamName: String) =
            TeamDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEAM_NAME, teamName)
                }
            }
    }
}