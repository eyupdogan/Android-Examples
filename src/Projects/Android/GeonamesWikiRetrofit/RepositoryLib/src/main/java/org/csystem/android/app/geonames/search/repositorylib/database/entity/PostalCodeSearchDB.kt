package org.csystem.android.app.geonames.search.repositorylib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postal_code_search")
class PostalCodeSearchDB
{
    @PrimaryKey @ColumnInfo(name = "postal_code") var postalCode:String = ""
    @ColumnInfo(name = "row_count") var rowCount:Int = 10
}