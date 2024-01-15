package org.csystem.android.app.wikisearch.di.module.datetime

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
    fun create():DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss")
    }
}