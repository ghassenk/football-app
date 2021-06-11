package com.gk.app.footballapp.di.app

import android.content.Context
import com.gk.app.footballapp.view.image.GlideImageLoader
import com.gk.app.footballapp.view.image.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideImageLoader(@ApplicationContext appContext: Context): ImageLoader {
        return GlideImageLoader()
    }
}
