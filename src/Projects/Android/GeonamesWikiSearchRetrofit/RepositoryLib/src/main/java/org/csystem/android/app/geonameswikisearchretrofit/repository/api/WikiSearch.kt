package org.csystem.android.app.geonameswikisearchretrofit.repository.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo

@Serializable
data class WikiSearch(@SerializedName("geonames") val wikiInfo: List<WikiInfo> = emptyList())