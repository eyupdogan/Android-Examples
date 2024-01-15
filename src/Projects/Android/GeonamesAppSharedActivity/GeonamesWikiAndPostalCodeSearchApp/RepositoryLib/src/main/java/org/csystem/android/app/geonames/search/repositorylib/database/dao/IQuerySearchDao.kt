package org.csystem.android.app.geonames.search.repositorylib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.geonames.search.repositorylib.database.entity.QuerySearch

@Dao
interface IQuerySearchDao
{
    @Insert
    fun save(querySearch: QuerySearch)

    @Query("SELECT * FROM query_searches qs WHERE qs.query_type = :queryType")
    fun getAllQuerySearch(queryType:String):List<QuerySearch>
}