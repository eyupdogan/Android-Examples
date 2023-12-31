/*--------------------------------------------------------------------------------------------------
    Sırada retrofit nesnesi yaratmak var ve biz bunu hilt ile yaratacağız
--------------------------------------------------------------------------------------------------*/
// WikiSearch
package org.csystem.android.app.wikisearchretrofit.api

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSearch(@SerializedName("geonames") val wikiInfo: List<WikiInfo> = emptyList())
