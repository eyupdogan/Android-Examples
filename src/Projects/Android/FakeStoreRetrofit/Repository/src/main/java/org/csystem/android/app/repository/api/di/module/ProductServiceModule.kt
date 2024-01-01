package org.csystem.android.app.repository.api.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.repository.api.IProductService
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object ProductServiceModule
{
    @Provides
    fun create(retrofit: Retrofit):IProductService
    {
        return retrofit.create(IProductService::class.java)
    }
}