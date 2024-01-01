package org.csystem.android.app.geonameswikisearchretrofit.repository.api.di.module.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.geonameswikisearchretrofit.repository.api.IGeonamesWikiSearchService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object GeonamesWikiSearchServiceModule
{
    @Provides
    fun create(retrofit: Retrofit):IGeonamesWikiSearchService
    {
        return retrofit.create(IGeonamesWikiSearchService::class.java)
    }
}