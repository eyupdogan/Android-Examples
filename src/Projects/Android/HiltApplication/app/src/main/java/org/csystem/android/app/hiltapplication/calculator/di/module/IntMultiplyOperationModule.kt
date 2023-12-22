package org.csystem.android.app.hiltapplication.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltapplication.calculator.IBinaryOperator
import org.csystem.android.app.hiltapplication.calculator.IntMultiplyOperation
import org.csystem.android.app.hiltapplication.calculator.di.module.annotation.IntMultiplyOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule
{
    @Binds
    @IntMultiplyOperationInterceptor
    abstract fun bindsIntMultiplyOperation(intMultiplyOperation: IntMultiplyOperation):IBinaryOperator<Int>
}