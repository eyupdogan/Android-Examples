package org.csystem.android.app.geonames.search.repositorylib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime

@Entity(
    tableName = "wiki_info", indices = [Index("q")],
    foreignKeys = [ForeignKey(
        WikiSearchDB::class, parentColumns = ["q"], childColumns = ["q"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE
    )]
)
data class WikiInfoDB(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var summary: String? = null,
    var q: String = "",
    var elevation: Int = 0,
    var longitude: Double = 0.0,
    var rank: Int = 0,
    var thumbnailImg: String? = null,
    var lang: String? = null,
    var title: String? = null,
    var latitude: Double = 0.0,
    var wikipediaUrl: String? = null,
    var geoNameId: Int = 0,
    var countryCode: String? = null,
    var feature: String? = null,
    @ColumnInfo(name = "search_date_time") var searchDateTime: LocalDateTime = LocalDateTime.now()
):Serializable