package org.csystem.android.app.geonames.search.wikisearch.api.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSearch(@SerializedName("geonames") var wikiInfo:List<WikiInfo> = emptyList())
