package org.csystem.android.app.hiltapplication.calculator.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltapplication.calculator.IBinaryOperator
import org.csystem.android.app.hiltapplication.calculator.IntAddOperation
import org.csystem.android.app.hiltapplication.calculator.di.module.annotation.IntAddOperationInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule
{
    @Binds
    @IntAddOperationInterceptor
    abstract fun binIntAddOperation(intAddOperation: IntAddOperation):IBinaryOperator<Int>
}