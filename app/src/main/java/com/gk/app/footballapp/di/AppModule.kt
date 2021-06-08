package com.gk.app.footballapp.di

import android.content.Context
import com.gk.app.footballapp.presenter.TeamSearchPresenter
import com.gk.app.footballapp.presenter.TeamDetailPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideLeagueSearchPresenter(@ApplicationContext appContext: Context): TeamSearchPresenter {
        TODO()
    }

    @Provides
    fun provideTeamDetailPresenter(@ApplicationContext appContext: Context): TeamDetailPresenter {
        TODO()
    }
}
