package org.csystem.android.app.repositorylib.api.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.repositorylib.api.IWikiSearchService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object WikiSearchServiceModule
{
    @Provides
    fun create(retrofit: Retrofit):IWikiSearchService
    {
        return retrofit.create(IWikiSearchService::class.java)
    }
}