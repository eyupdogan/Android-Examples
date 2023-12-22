// IntAddOperationModule
package org.csystem.android.app.hiltrepeat.di.module.calculate

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltrepeat.calculate.IBinaryOperation
import org.csystem.android.app.hiltrepeat.calculate.IntAddOperation
import org.csystem.android.app.hiltrepeat.di.module.calculate.annotation.IntAddOperationModuleInterceptor

@Module
@InstallIn(ActivityComponent::class)
abstract class IntAddOperationModule
{
    @Binds
    @IntAddOperationModuleInterceptor
    abstract fun bindIntAddOperation(intAddOperation: IntAddOperation): IBinaryOperation<Int>
}