package org.csystem.android.app.wiki.search.di.module.datetime

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateTimeFormatterModule
{
    @Provides
    @Singleton
    fun provideFormatter():DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
}