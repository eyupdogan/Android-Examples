package org.csystem.android.app.geonameswikisearchretrofit.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.geonameswikisearchretrofit.repository.dao.IWikiInfoDao
import org.csystem.android.app.geonameswikisearchretrofit.repository.database.WikiSearchDatabase

@Module
@InstallIn(ActivityComponent::class)
object WikiInfoDaoModule
{
    @Provides
    fun createWikiInfoDao(wikiSearchDatabase: WikiSearchDatabase):IWikiInfoDao
    {
        return wikiSearchDatabase.createWikiInfoDao()
    }
}