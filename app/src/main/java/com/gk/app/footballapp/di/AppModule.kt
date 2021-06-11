package com.gk.app.footballapp.di

import com.gk.app.footballapp.view.image.GlideImageLoader
import com.gk.app.footballapp.view.image.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideImageLoader(): ImageLoader {
        return GlideImageLoader()
    }
}
