package org.csystem.android.app.repositorylib.api.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSearch (@SerializedName("geonames") val wikiInfo:List<WikiInfo> = emptyList())
{
}