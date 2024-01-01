package org.csystem.android.app.geonameswikisearchretrofit.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo

@Dao
interface IWikiInfoDao
{
    @Insert
    fun save(wikiInfo: WikiInfo)

    @Query("SELECT EXISTS(SELECT * FROM wiki_info WHERE lng = :lng AND lat = :lat)")
    fun existByLngAndLat(lng:Double, lat:Double):Boolean
}