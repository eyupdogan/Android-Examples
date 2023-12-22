package org.csystem.android.app.randomgenerator.di.module.scheduler

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulerModule
{
    @Provides // neden provides çünkü önceden enjekte edeceğini bildiği bir sınıf yok
    @Singleton
    fun createScheduler():ScheduledExecutorService = Executors.newScheduledThreadPool(2)
}