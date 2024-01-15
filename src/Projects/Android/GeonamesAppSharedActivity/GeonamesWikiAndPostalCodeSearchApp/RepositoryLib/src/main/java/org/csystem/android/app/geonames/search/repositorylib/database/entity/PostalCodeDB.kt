package org.csystem.android.app.geonames.search.repositorylib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "postal_code", indices = [Index("postal_code")],
    foreignKeys = [ForeignKey(entity = PostalCodeSearchDB::class, parentColumns = ["postal_code"],
        childColumns = ["postal_code"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
class PostalCodeDB
{
    @PrimaryKey(autoGenerate = true) var id:Long = 0
    @ColumnInfo(name = "admin_code_1") var adminCode1: String? = null
    @ColumnInfo(name = "admin_name_2") var adminName2: String? = null
    var longitude:Double = 0.0
    var countryCode: String? = null
    @ColumnInfo(name = "postal_code") var postalCode: String? = null
    @ColumnInfo(name = "admin_name_1") var adminName1: String? = null
    var placeName: String? = null
    var latitude:Double = 0.0
    @ColumnInfo(name = "search_date_time") var searchDateTime: LocalDateTime = LocalDateTime.now()
}