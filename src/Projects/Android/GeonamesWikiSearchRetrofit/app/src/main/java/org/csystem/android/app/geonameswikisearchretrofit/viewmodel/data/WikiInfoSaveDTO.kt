package org.csystem.android.app.geonameswikisearchretrofit.viewmodel.data

import java.io.Serializable

data class WikiInfoSaveDTO(var summary: String? = null,
                           var elevation:Int = 0,
                           var lng:Double = 0.0,
                           var rank:Int = 0,
                           var thumbnailImg: String? = null,
                           var lang: String? = null,
                           var title: String? = null,
                           var lat:Double = 0.0,
                           var wikipediaUrl: String? = null,
                           var geoNameId:Int = 0,
                           var countryCode: String? = null,
                           var feature: String? = null):Serializable
{
    override fun toString(): String = summary ?: "Yeterince bilgi yok"
}