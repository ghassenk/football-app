package com.gk.app.footballapp.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.gk.app.footballapp.R
import com.gk.app.footballapp.view.search.TeamSearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teamSearchFragment = TeamSearchFragment.newInstance(2)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, teamSearchFragment)
            .commit()
    }

}