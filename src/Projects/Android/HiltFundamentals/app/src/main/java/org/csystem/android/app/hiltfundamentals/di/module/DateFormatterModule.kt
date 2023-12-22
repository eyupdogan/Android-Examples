package org.csystem.android.app.hiltfundamentals.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltfundamentals.di.annotation.DateFormatterInterceptor
import java.time.format.DateTimeFormatter

@Module
@InstallIn(ActivityComponent::class)
object DateFormatterModule
{
    @Provides
    @DateFormatterInterceptor
    fun provideFormatter(): DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

}