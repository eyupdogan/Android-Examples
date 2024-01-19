package org.csystem.android.app.payment.repository.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.payment.repository.UserRepository
import org.csystem.android.app.payment.repository.dao.IUserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule
{
    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository):IUserRepository
}