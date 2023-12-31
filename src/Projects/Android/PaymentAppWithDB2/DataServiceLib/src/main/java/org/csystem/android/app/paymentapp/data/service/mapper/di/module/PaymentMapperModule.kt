package org.csystem.android.app.paymentapp.data.service.mapper.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.paymentapp.data.service.mapper.IPaymentMapper
import org.mapstruct.factory.Mappers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaymentMapperModule
{
    @Provides
    @Singleton
    fun providePaymentMapper(): IPaymentMapper = Mappers.getMapper(IPaymentMapper::class.java)
}