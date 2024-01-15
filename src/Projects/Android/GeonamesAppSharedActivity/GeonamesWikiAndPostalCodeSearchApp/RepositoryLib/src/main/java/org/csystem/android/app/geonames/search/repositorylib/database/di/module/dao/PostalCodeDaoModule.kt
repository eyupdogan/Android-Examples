package org.csystem.android.app.geonames.search.repositorylib.database.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.geonames.search.repositorylib.database.GeonamesDatabase
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IPostalCodeSearchDao

@Module
@InstallIn(ActivityComponent::class)
object PostalCodeDaoModule
{
    @Provides
    fun createPostalCodeDao(geonamesDatabase: GeonamesDatabase):IPostalCodeSearchDao
    {
        return geonamesDatabase.createPostalCodeSearchDao()
    }
}