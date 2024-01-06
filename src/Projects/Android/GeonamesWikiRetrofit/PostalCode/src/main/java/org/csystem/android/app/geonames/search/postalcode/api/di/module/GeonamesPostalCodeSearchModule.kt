package org.csystem.android.app.geonames.search.postalcode.api.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.geonames.search.postalcode.api.IPostalCodeService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object GeonamesPostalCodeSearchModule
{
    @Provides
    fun create(retrofit: Retrofit):IPostalCodeService
    {
        return retrofit.create(IPostalCodeService::class.java)
    }
}