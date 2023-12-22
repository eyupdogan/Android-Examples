package org.csystem.android.app.payment.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.payment.repository.dao.ILoginInfoDao
import org.csystem.android.app.payment.repository.dao.IPaymentDao
import org.csystem.android.app.payment.repository.dao.IUserDao
import org.csystem.android.app.payment.repository.database.PaymentApplicationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginInfoDaoModule
{
    @Provides
    @Singleton
    fun createLoginInfoDao(paymentApplicationDatabase: PaymentApplicationDatabase):ILoginInfoDao
    {
        return paymentApplicationDatabase.createLoginInfoDao()
    }
}