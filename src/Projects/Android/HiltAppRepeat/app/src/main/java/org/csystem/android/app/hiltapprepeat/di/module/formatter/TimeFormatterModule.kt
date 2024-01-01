package org.csystem.android.app.hiltapprepeat.di.module.formatter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.TimeFormatterInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TimeFormatterModule
{
    @Provides
    @Singleton
    @TimeFormatterInterceptor
    fun provideFormatter():DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("kk:mm:ss")
    }
}