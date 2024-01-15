package org.csystem.android.app.geonames.search.repositorylib.database.di.module.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.geonames.search.repositorylib.database.GeonamesDatabase

@Module
@InstallIn(SingletonComponent::class)
object GeonamesDatabaseModule
{
    @Provides
    fun createGeonamesDatabase(@ApplicationContext context: Context): GeonamesDatabase
    {
        return Room.databaseBuilder(context, GeonamesDatabase::class.java, "geonamesdb.sqlite3")
            .allowMainThreadQueries()
            .build()
    }
}