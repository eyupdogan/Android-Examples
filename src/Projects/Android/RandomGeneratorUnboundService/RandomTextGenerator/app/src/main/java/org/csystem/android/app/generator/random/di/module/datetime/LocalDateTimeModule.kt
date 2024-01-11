package org.csystem.android.app.generator.random.di.module.datetime

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import java.time.LocalDateTime

@Module
@InstallIn(ServiceComponent::class)
object LocalDateTimeModule
{
    @Provides
    fun create():LocalDateTime = LocalDateTime.now()
}