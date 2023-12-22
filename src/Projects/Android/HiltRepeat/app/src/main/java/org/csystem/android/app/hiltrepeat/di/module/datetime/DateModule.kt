package org.csystem.android.app.hiltrepeat.di.module.datetime

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalDate

@Module
@InstallIn(ActivityComponent::class)
object DateModule
{
    @Provides
    fun provideDateTime():LocalDate = LocalDate.now()
}