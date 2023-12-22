// IntMultiplyOperationModule
package org.csystem.android.app.hiltrepeat.di.module.calculate

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltrepeat.calculate.IBinaryOperation
import org.csystem.android.app.hiltrepeat.calculate.IntMultiplyOperation
import org.csystem.android.app.hiltrepeat.di.module.calculate.annotation.IntMultiplyOperationModuleInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntMultiplyOperationModule
{
    @Binds
    @IntMultiplyOperationModuleInterceptor
    abstract fun bindMultiplyOperationModule(intMultiplyOperation: IntMultiplyOperation): IBinaryOperation<Int>
}