package org.csystem.android.app.geonames.search.repositorylib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiInfoDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiSearchDB

@Dao
interface IWikiInfoSearchDao
{
    @Insert
    fun save(wikiInfoDB: WikiInfoDB)

    @Insert
    fun saveWikiSearch(wikiSearchDB: WikiSearchDB)

    @Query("SELECT query_count FROM wiki_searches WHERE q = :queryName")
    fun getQueryCountByQ(queryName:String):Int

    @Query("SELECT EXISTS(SELECT * FROM wiki_searches wi WHERE wi.q = :q AND wi.query_count >= :rowCount )")
    fun existsWikiInfoByQAndRowCount(q:String, rowCount:Int):Boolean

    @Query("""SELECT * FROM wiki_searches ws INNER JOIN wiki_info wi ON ws.q = wi.q
        WHERE ws.q = :q AND ws.query_count = :rowCount
    """)
    fun findWikiInfoByWikiSearch(q:String, rowCount: Int):List<WikiInfoDB>
}