package org.csystem.android.app.hiltapprepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltapprepeat.databinding.ActivityCalculateBinding
import org.csystem.android.app.hiltapprepeat.di.module.calculate.IBinaryOperation
import org.csystem.android.app.hiltapprepeat.di.module.calculate.annotation.IntAddOperationInterceptor
import org.csystem.android.app.hiltapprepeat.viewmodel.data.OperationInfo
import org.csystem.android.app.hiltapprepeat.viewmodel.listeners.CalculateActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class CalculateActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityCalculateBinding

    @Inject
    @IntAddOperationInterceptor
    lateinit var intBinaryOperator:IBinaryOperation<Int>

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_calculate)
    }

    private fun initData()
    {
        mBinding.viewModel = CalculateActivityListenerViewModel(this)
        mBinding.operationInfo = OperationInfo()
        mBinding.result = ""
    }

    private fun initialize()
    {
        initBinding()
        initData()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun calculateButtonClicked()
    {
        if(intBinaryOperator.isValid(mBinding.operationInfo!!.op)){
            mBinding.result = intBinaryOperator.applyAsInt(mBinding.operationInfo!!.first, mBinding.operationInfo!!.second).toString()
        }
    }

}