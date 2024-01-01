package org.csystem.android.app.geonameswikisearchretrofit.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo

@Dao
interface IWikiInfoDao
{
    @Insert
    fun save(wikiInfo: WikiInfo)
}