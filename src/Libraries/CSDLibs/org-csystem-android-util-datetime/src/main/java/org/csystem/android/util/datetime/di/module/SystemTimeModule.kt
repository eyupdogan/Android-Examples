package org.csystem.android.util.datetime.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.util.datetime.di.module.annotation.SystemLocalTimeInterceptor
import java.time.LocalTime

@Module
@InstallIn(ActivityComponent::class)
object SystemTimeModule {
    @Provides
    @SystemLocalTimeInterceptor
    fun provideTime():LocalTime = LocalTime.now()
}