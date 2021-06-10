package com.gk.app.footballapp.di

import android.content.Context
import com.gk.app.football.data.gateway.TeamGatewayImpl
import com.gk.app.football.domain.gateway.TeamGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideTeamGateway(@ApplicationContext appContext: Context): TeamGateway {
        return TeamGatewayImpl("https://www.thesportsdb.com/api/v1/json/1/")
    }
}
