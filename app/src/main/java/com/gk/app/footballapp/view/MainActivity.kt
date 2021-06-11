package com.gk.app.footballapp.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.gk.app.footballapp.R
import com.gk.app.footballapp.view.detail.TeamDetailFragment
import com.gk.app.footballapp.view.search.TeamListItem
import com.gk.app.footballapp.view.search.TeamSearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamSearchFragment = TeamSearchFragment.newInstance(2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, teamSearchFragment)
            .commit()
    }

    override fun showTeamDetailView(team: TeamListItem) {
        val teamDetailFragment = TeamDetailFragment.newInstance()

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, teamDetailFragment)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}