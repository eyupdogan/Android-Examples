package org.csystem.android.app.fakestoreretro.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.mapstruct.factory.Mappers

@Module
@InstallIn(ActivityComponent::class)
class ProductMapperModule
{
    @Provides
    fun provideProductMapper():IProductMapper = Mappers.getMapper(IProductMapper::class.java)

}