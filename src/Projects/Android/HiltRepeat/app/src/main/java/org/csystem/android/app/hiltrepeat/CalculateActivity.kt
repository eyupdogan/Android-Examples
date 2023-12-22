// CalculateActivity
package org.csystem.android.app.hiltrepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltrepeat.calculate.IBinaryOperation
import org.csystem.android.app.hiltrepeat.databinding.ActivityCalculateBinding
import org.csystem.android.app.hiltrepeat.di.module.calculate.annotation.IntAddOperationModuleInterceptor
import org.csystem.android.app.hiltrepeat.viewModel.CalculateActivityListenerViewModel
import org.csystem.android.app.hiltrepeat.viewModel.OperationInfo
import javax.inject.Inject

@AndroidEntryPoint
class CalculateActivity : AppCompatActivity()
{
    @IntAddOperationModuleInterceptor
    @Inject lateinit var intBinaryOperator: IBinaryOperation<Int>

    private lateinit var mBinding: ActivityCalculateBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_calculate)
        mBinding.viewModel = CalculateActivityListenerViewModel(this)
        mBinding.operationInfo = OperationInfo()
    }

    private fun initialize()
    {
        initBinding()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun calculateButtonClicked()
    {
        mBinding.result = intBinaryOperator.applyAsInt(mBinding.operationInfo!!.first, mBinding.operationInfo!!.second).toString()
    }
}