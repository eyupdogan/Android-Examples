package org.csystem.android.app.hiltapprepeat.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalDateTime

@Module
@InstallIn(ActivityComponent::class)
object DateTimeModule
{
    @Provides
    fun provideDateTime():LocalDateTime = LocalDateTime.now()
}