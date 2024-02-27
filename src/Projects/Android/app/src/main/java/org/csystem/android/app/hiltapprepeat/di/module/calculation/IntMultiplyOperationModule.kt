package org.csystem.android.app.hiltapprepeat.di.module.calculation

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltapprepeat.di.module.calculation.IBinaryOperation
import org.csystem.android.app.hiltapprepeat.di.module.calculation.IMultiplyOperation
import org.csystem.android.app.hiltapprepeat.di.module.calculation.annotation.IntMultiplyOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule
{
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindMultiplyAddOperation(intMultiplyOperation: IMultiplyOperation): IBinaryOperation<Int>
}