package org.csystem.android.app.hiltfundamentals.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltfundamentals.di.annotation.TimeFormatterInterceptor
import java.time.format.DateTimeFormatter

@Module
@InstallIn(ActivityComponent::class)
object TimeFormatterModule
{
    @Provides
    @TimeFormatterInterceptor
    fun provideFormatter():DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("kk:mm:ss")
    }
}