package com.gk.app.footballapp.di.data

import com.gk.app.football.data.gateway.TeamGatewayImpl
import com.gk.app.football.domain.gateway.TeamGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideTeamGateway(): TeamGateway {
        return TeamGatewayImpl("https://www.thesportsdb.com/api/v1/json/1/")
    }
}
