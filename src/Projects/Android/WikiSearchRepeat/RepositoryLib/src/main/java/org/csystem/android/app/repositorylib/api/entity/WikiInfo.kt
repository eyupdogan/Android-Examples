package org.csystem.android.app.repositorylib.api.entity

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
class WikiInfo {
    var id:Long = 0
    var summary: String? = null
    var elevation = 0
    var lng = 0.0
    var rank = 0
    var thumbnailImg: String? = null
    var lang: String? = null
    var title: String? = null
    var lat = 0.0
    var wikipediaUrl: String? = null
    var geoNameId = 0
    var countryCode: String? = null
    var feature: String? = null
    var wikiSearchDateTime: LocalDateTime = LocalDateTime.now()

    override fun toString() = "Summary:${summary?.substring(0, 10)}, $lng, $lat"
}
