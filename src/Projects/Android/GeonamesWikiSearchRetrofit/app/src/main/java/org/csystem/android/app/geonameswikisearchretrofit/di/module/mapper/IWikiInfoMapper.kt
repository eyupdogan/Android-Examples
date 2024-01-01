package org.csystem.android.app.geonameswikisearchretrofit.di.module.mapper

import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo
import org.csystem.android.app.geonameswikisearchretrofit.viewmodel.data.WikiInfoSaveDTO
import org.mapstruct.Mapper

@Mapper(implementationName = "WikiInfoMapperImpl")
interface IWikiInfoMapper
{
    fun toWikiInfoSaveDTO(wikiInfo: WikiInfo):WikiInfoSaveDTO
}