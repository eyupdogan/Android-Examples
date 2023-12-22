package org.csystem.android.app.hiltrepeat.di.module.datetime

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
    fun provideDateTime():LocalTime = LocalTime.now()
}