package com.gk.app.footballapp.di

import android.content.Context
import androidx.fragment.app.Fragment
import com.gk.app.footballapp.presenter.TeamDetailPresenter
import com.gk.app.footballapp.presenter.TeamDetailPresenterImpl
import com.gk.app.footballapp.presenter.TeamSearchPresenter
import com.gk.app.footballapp.presenter.TeamSearchPresenterImpl
import com.gk.app.footballapp.view.detail.TeamDetailView
import com.gk.app.footballapp.view.image.GlideImageLoader
import com.gk.app.footballapp.view.image.ImageLoader
import com.gk.app.footballapp.view.search.TeamSearchView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideLeagueSearchPresenter(fragment: Fragment): TeamSearchPresenter {
        return TeamSearchPresenterImpl(fragment as TeamSearchView)
    }

    @Provides
    fun provideTeamDetailPresenter(fragment: Fragment): TeamDetailPresenter {
        return TeamDetailPresenterImpl(fragment as TeamDetailView)
    }

    @Provides
    fun provideImageLoader(@ApplicationContext appContext: Context): ImageLoader {
        return GlideImageLoader()
    }
}
