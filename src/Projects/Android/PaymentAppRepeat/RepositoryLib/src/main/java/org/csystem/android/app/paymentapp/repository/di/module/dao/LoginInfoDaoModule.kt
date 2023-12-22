package org.csystem.android.app.paymentapp.repository.di.module.dao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.paymentapp.repository.dao.ILoginInfoDao
import org.csystem.android.app.paymentapp.repository.database.PaymentApplicationDatabase

@Module
@InstallIn(ActivityComponent::class)
object LoginInfoDaoModule
{
    @Provides
    fun createLoginInfoDao(paymentApplicationDatabase: PaymentApplicationDatabase):ILoginInfoDao
    {
        return paymentApplicationDatabase.createLoginInfoDao()
    }
}