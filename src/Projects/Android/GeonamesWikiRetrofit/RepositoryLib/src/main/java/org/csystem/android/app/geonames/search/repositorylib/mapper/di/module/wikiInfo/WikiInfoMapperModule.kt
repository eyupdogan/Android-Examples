package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.wikiInfo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.mapstruct.factory.Mappers

@Module
@InstallIn(ActivityComponent::class)
object WikiInfoMapperModule
{
    @Provides
    fun provideWikiInfoMapper(): IWikiInfoMapper = Mappers.getMapper(IWikiInfoMapper::class.java)
}