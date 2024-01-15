package org.csystem.android.app.geonames.search.repositorylib.database.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.geonames.search.repositorylib.database.GeonamesDatabase
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IWikiInfoSearchDao

@Module
@InstallIn(ActivityComponent::class)
object WikiSearchDaoModule
{
    @Provides
    fun createWikiSearchDao(geonamesDatabase: GeonamesDatabase):IWikiInfoSearchDao
    {
        return geonamesDatabase.createWikiSearchDao()
    }
}