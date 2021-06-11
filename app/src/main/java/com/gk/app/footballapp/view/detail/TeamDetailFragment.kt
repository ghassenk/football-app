package com.gk.app.footballapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gk.app.footballapp.R
import com.gk.app.footballapp.presenter.TeamDetailPresenter
import com.gk.app.footballapp.view.search.TeamSearchFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TeamDetailFragment : Fragment() , TeamDetailView{

    @Inject
    lateinit var teamDetailPresenter: TeamDetailPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    companion object {

        // TODO add item id
        @JvmStatic
        fun newInstance() =
            TeamDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
            }
    }

    override fun onViewDestroyed() {
        TODO("Not yet implemented")
    }

    override fun updateDetails(imageUrl: String, title: String, description: String) {
        TODO("Not yet implemented")
    }
}