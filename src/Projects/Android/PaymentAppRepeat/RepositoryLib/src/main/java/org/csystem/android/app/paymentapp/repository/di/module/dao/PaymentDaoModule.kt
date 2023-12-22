package org.csystem.android.app.paymentapp.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.paymentapp.repository.dao.IPaymentDao
import org.csystem.android.app.paymentapp.repository.database.PaymentApplicationDatabase

@Module
@InstallIn(ActivityComponent::class)
object PaymentDaoModule
{
    @Provides
    fun createPaymentDao(paymentApplicationDatabase: PaymentApplicationDatabase):IPaymentDao
    {
        return paymentApplicationDatabase.createPaymentDao()
    }
}