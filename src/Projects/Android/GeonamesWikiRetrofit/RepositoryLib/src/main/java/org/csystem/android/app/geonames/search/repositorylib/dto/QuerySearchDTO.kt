package org.csystem.android.app.geonames.search.repositorylib.dto

import java.io.Serializable
import java.time.LocalDateTime

data class QuerySearchDTO(
    var query: String = "",
    var queryType: String = "",
    var rowCount: Int = 0,
    var queryDateTime: LocalDateTime = LocalDateTime.now()
) : Serializable
{
    override fun toString(): String
    {
        return "queryType = $queryType,\r\nquery = $query,\r\nrowCount = $rowCount,\r\nqueryDateTime = $queryDateTime"
    }
}