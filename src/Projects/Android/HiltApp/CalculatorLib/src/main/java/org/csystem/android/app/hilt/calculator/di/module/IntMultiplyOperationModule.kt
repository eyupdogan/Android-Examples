package org.csystem.android.app.hilt.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hilt.calculator.di.IBinaryOperator
import org.csystem.android.app.hilt.calculator.di.IntMultiplyOperation
import org.csystem.android.app.hilt.calculator.di.module.annotation.IntMultiplyOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule {
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindIntMultiplyOperation(intMultiplyOperation: IntMultiplyOperation): IBinaryOperator<Int>
}