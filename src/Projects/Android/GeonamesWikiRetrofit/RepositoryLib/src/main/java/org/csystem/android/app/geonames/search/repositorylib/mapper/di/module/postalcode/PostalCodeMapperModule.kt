package org.csystem.android.app.geonames.search.repositorylib.mapper.di.module.postalcode

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.mapstruct.factory.Mappers

@Module
@InstallIn(ActivityComponent::class)
object PostalCodeMapperModule
{
    @Provides
    fun providePostalCodeMapper(): IPostalCodeMapper = Mappers.getMapper(IPostalCodeMapper::class.java)
}