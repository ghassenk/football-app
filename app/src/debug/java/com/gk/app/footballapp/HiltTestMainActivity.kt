package com.gk.app.footballapp

import androidx.appcompat.app.AppCompatActivity
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.search.TeamListItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HiltTestMainActivity : AppCompatActivity(), MainView {

    override fun showTeamDetailView(team: TeamListItem) {

    }

}