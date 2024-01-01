package org.csystem.android.app.fakestore.di.module.mapper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductMapperModule
{
    @Provides
    @Singleton
    fun provideProductMapper():IProductMapper = Mappers.getMapper(IProductMapper::class.java)
}