package org.csystem.android.app.geonames.search.postalcode.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class PostalCodeSearch(var postalCodes:List<PostalCode> = emptyList())
