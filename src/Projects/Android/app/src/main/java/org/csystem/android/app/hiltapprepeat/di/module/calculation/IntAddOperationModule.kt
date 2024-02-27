package org.csystem.android.app.hiltapprepeat.di.module.calculation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltapprepeat.di.module.calculation.IBinaryOperation
import org.csystem.android.app.hiltapprepeat.di.module.calculation.IntAddOperation
import org.csystem.android.app.hiltapprepeat.di.module.calculation.annotation.IntAddOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule
{
    @Binds
    @IntAddOperationInterceptor
    abstract fun bindIntAddOperation(intAddOperation: IntAddOperation): IBinaryOperation<Int>
}