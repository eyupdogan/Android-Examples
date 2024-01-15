package org.csystem.android.app.geonames.search.wikisearch.api.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class WikiInfo
{
    var summary: String? = null
    var elevation = 0
    @SerializedName("lng") var longitude = 0.0
    var rank = 0
    var thumbnailImg: String? = null
    var lang: String? = null
    var title: String? = null
    @SerializedName("lat") var latitude = 0.0
    var wikipediaUrl: String? = null
    var geoNameId = 0
    var countryCode: String? = null
    var feature: String? = null

    override fun toString() = "Summary:${summary?.substring(0, 10)}, $longitude, $latitude"
}
