package org.csystem.android.app.geonames.search.repositorylib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "query_searches")
class QuerySearch
{
    @PrimaryKey(autoGenerate = true) var id:Long = 0
    @ColumnInfo(name = "query") var query: String = ""
    @ColumnInfo(name = "query_type") var queryType:String = ""
    @ColumnInfo(name = "row_count") var rowCount:Int = 0
    @ColumnInfo(name = "query_date_time") var queryDateTime:LocalDateTime = LocalDateTime.now()
}