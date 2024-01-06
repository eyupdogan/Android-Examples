package org.csystem.android.app.geonames.search.repositorylib.dto

import java.io.Serializable

data class PostalCodeListDBDTO(
    var postalCode:String = "",var rowCount:Int = 0
) : Serializable {
    override fun toString(): String
    {
        return "$postalCode, $rowCount"
    }
}