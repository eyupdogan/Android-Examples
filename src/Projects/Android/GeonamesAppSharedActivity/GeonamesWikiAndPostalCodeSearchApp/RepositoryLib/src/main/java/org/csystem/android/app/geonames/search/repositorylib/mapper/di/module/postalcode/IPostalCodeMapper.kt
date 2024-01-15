package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.postalcode


import org.csystem.android.app.geonames.search.postalcode.api.entity.PostalCode
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeSearchDB
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeListDBDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(implementationName = "PostalCodeMapperImpl", )
interface IPostalCodeMapper
{
    @Mapping(source = "adminName1", target = "adminName")
    fun toPostalCodeDTO(postalCode: PostalCode): PostalCodeDTO

    fun toPostalCodeDBDTO(postalCode: PostalCode): PostalCodeDB

    fun toPostalCodeSearchSaveDTO(postalCodeListDBDTO: PostalCodeListDBDTO):PostalCodeSearchDB

    fun toPostalCode(postalCodeDB: PostalCodeDB):PostalCode
}