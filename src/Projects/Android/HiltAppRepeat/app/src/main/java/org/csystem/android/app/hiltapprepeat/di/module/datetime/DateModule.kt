package org.csystem.android.app.hiltapprepeat.di.module.datetime

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
    fun provideDate(): LocalDate = LocalDate.now()
}