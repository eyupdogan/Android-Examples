package org.csystem.android.app.hiltapprepeat.di.module.calculate

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltapprepeat.di.module.calculate.annotation.IntMultiplyOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule
{
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindIntMultiplyOperation(intMultiplyOperation: IntMultiplyOperation):IBinaryOperation<Int>
}