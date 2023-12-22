package org.csystem.android.app.hiltrepeat.di.module.formatter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.hiltrepeat.di.module.formatter.annotation.DateFormatterInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateFormatterModule
{
    @Provides
    @Singleton
    @DateFormatterInterceptor
    fun provideFormatter():DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}