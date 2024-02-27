package org.csystem.android.app.hiltapprepeat.di.module.formatter

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.DateTimeFormatterInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateTimeFormatterModule
{
    @Provides
    @Singleton
    @DateTimeFormatterInterceptor
    fun provideFormatter():DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
}