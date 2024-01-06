package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.querysearch

import org.csystem.android.app.geonames.search.repositorylib.database.entity.QuerySearch
import org.csystem.android.app.geonames.search.repositorylib.dto.QuerySearchDTO
import org.mapstruct.Mapper

@Mapper(implementationName = "QuerySearchImpl")
interface IQuerySearchMapper
{
    fun toQuerySearchDTO(querySearch: QuerySearch):QuerySearchDTO

    fun toQuerySearch(querySearchDTO: QuerySearchDTO):QuerySearch
}