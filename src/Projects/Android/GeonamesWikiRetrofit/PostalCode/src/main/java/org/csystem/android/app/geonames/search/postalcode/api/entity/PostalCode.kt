package org.csystem.android.app.geonames.search.postalcode.api.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class PostalCode
{
    var adminCode1: String? = null
    var adminName2: String? = null
    @SerializedName("lng") var longitude:Double = 0.0
    var countryCode: String? = null
    var postalCode: String? = null
    var adminName1: String? = null
    var placeName: String? = null
    @SerializedName("lat") var latitude:Double = 0.0

    override fun toString(): String = "$adminCode1, $postalCode, $placeName"
}
