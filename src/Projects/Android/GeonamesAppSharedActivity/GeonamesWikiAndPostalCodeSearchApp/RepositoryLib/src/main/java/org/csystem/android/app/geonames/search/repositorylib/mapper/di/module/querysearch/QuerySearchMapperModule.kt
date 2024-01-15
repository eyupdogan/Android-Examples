package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.querysearch

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.mapstruct.factory.Mappers

@Module
@InstallIn(ActivityComponent::class)
object QuerySearchMapperModule
{
    @Provides
    fun provideQuerySearchMapper():IQuerySearchMapper = Mappers.getMapper(IQuerySearchMapper::class.java)
}