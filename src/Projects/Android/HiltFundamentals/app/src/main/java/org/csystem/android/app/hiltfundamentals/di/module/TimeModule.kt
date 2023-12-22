package org.csystem.android.app.hiltfundamentals.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalTime

@Module
@InstallIn(ActivityComponent::class)
object TimeModule
{
    @Provides
    fun provideTime(): LocalTime = LocalTime.now()

}