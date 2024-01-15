package org.csystem.android.app.geonames.search.repositorylib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("wiki_searches")
class WikiSearchDB
{
    @PrimaryKey var q:String = ""
    @ColumnInfo(name = "query_count") var queryCount:Int = 10
}