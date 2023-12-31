package org.csystem.android.app.geonameswikisearchretrofit.repository.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSearch(@SerializedName("geonames") val wikiInfo:List<WikiInfo> = emptyList())
