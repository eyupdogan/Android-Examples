package org.csystem.android.app.geonameswikisearchretrofit.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "wiki_info")
class WikiInfo {
    @PrimaryKey(autoGenerate = true) var id:Long = 0
    var summary: String? = null
    var elevation = 0
    var lng = 0.0
    var rank = 0
    @ColumnInfo(name = "thumbnail_img") var thumbnailImg: String? = null
    var lang: String? = null
    var title: String? = null
    var lat = 0.0
    @ColumnInfo(name = "wikipedia_url") var wikipediaUrl: String? = null
    var geoNameId = 0
    @ColumnInfo(name = "country_code") var countryCode: String? = null
    var feature: String? = null

    override fun toString() = "Summary:${summary?.substring(0, 10)}, $lng, $lat"
}
