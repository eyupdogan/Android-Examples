package org.csystem.android.app.payment.repository.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.payment.repository.LoginInfoRepository
import org.csystem.android.app.payment.repository.dao.ILoginInfoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginInfoRepositoryModule
{
    @Binds
    @Singleton
    abstract fun bindLoginInfoRepository(loginInfoRepository: LoginInfoRepository):ILoginInfoRepository
}