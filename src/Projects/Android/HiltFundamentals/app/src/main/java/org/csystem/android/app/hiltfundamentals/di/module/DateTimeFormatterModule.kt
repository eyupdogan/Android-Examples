package org.csystem.android.app.hiltfundamentals.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.hiltfundamentals.di.annotation.DateTimeFormatterInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateTimeFormatterModule
{
    @Provides
    @Singleton
    @DateTimeFormatterInterceptor
    fun provideFormatter():DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    }
}