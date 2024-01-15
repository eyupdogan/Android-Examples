package org.csystem.android.app.geonames.search.repositorylib.dto

import java.io.Serializable

data class WikiInfoDTO(
    var summary: String? = null,
    var longitude: Double = 0.0,
    var thumbnailImg: String? = null,
    var lang: String? = null,
    var title: String? = null,
    var latitude: Double = 0.0,
    var wikipediaUrl: String? = null
) : Serializable
