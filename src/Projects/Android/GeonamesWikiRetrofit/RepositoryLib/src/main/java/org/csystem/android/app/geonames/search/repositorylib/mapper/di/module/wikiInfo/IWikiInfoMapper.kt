package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.wikiInfo

import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiSearchDB
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiInfoDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiSearchDBDTO
import org.csystem.android.app.geonames.search.wikisearch.api.entity.WikiInfo
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiInfoDB as WikiInfoSave

@Mapper(implementationName = "WikiInfoMapperImpl")
interface IWikiInfoMapper
{
    fun toWikiInfoDTO(wikiInfo: WikiInfo): WikiInfoDTO

    @Mapping(source = "q", target = "q")
    fun toWikiInfoSaveDTO(wikiInfo: WikiInfo, q:String):WikiInfoSave

    fun toWikiInfo(wikiInfoSave:WikiInfoSave):WikiInfo

    fun toWikiSearch(wikiSearchDBDTO: WikiSearchDBDTO):WikiSearchDB
}