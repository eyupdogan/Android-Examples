package org.csystem.android.app.geonameswikisearchretrofit.repository.di.module.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.geonameswikisearchretrofit.repository.database.WikiSearchDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WikiSearchDatabaseModule
{
    @Provides
    @Singleton
    fun createWikiSearchDatabase(@ApplicationContext context: Context):WikiSearchDatabase
    {
        return Room.databaseBuilder(context, WikiSearchDatabase::class.java, "wikiSearchDb.sqlite3")
            .allowMainThreadQueries().build()
    }
}