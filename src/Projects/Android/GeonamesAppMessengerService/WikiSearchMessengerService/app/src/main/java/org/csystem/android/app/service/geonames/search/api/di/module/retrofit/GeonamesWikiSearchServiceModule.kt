package org.csystem.android.app.service.geonames.search.api.di.module.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.service.geonames.search.api.IGeonamesWikiSearchService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeonamesWikiSearchServiceModule
{
    @Provides
    @Singleton
    fun create(retrofit: Retrofit):IGeonamesWikiSearchService
    {
        return retrofit.create(IGeonamesWikiSearchService::class.java)
    }
}