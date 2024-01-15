package org.csystem.android.app.geonames.search.repositorylib.dto

import java.io.Serializable

data class PostalCodeDTO(
    var postalCode: String? = null,
    var adminName: String? = null,
    var placeName: String? = null,
) : Serializable