package com.gk.app.footballapp.di.app

import androidx.fragment.app.Fragment
import com.gk.app.football.data.gateway.TeamGatewayImpl
import com.gk.app.football.domain.gateway.TeamGateway
import com.gk.app.footballapp.presenter.TeamDetailPresenter
import com.gk.app.footballapp.presenter.TeamDetailPresenterImpl
import com.gk.app.footballapp.presenter.TeamSearchPresenter
import com.gk.app.footballapp.presenter.TeamSearchPresenterImpl
import com.gk.app.footballapp.view.MainView
import com.gk.app.footballapp.view.detail.TeamDetailView
import com.gk.app.footballapp.view.search.TeamSearchView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideLeagueSearchPresenter(
        fragment: Fragment
    ): TeamSearchPresenter {
        return TeamSearchPresenterImpl(
            fragment as TeamSearchView,
            TeamGatewayImpl("https://www.thesportsdb.com/api/v1/json/1/"),
            fragment.activity as MainView
        )
    }

    @Provides
    fun provideTeamDetailPresenter(fragment: Fragment): TeamDetailPresenter {
        return TeamDetailPresenterImpl(fragment as TeamDetailView)
    }
}