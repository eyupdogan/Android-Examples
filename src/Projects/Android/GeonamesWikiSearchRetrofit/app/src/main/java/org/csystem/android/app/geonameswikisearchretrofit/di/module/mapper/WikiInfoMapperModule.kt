package org.csystem.android.app.geonameswikisearchretrofit.di.module.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WikiInfoMapperModule
{
    @Provides
    @Singleton
    fun provideWikiInfoMapper():IWikiInfoMapper = Mappers.getMapper(IWikiInfoMapper::class.java)
}