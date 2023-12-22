package org.csystem.android.app.hiltapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltapplication.calculator.IBinaryOperator
import org.csystem.android.app.hiltapplication.calculator.di.module.annotation.IntAddOperationInterceptor
import org.csystem.android.app.hiltapplication.databinding.ActivityMainBinding
import org.csystem.android.app.hiltapplication.datetime.DateInfo
import org.csystem.android.app.hiltapplication.datetime.DateTimeInfo
import org.csystem.android.app.hiltapplication.datetime.TimeInfo
import org.csystem.android.app.hiltapplication.viewmodel.MainActivityListenerViewModel
import org.csystem.android.app.hiltapplication.viewmodel.OperationInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.operationInfo = OperationInfo()
    }

    private fun initialize()
    {
        showDateTime()
        initBinding()
    }

    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo: TimeInfo

    @Inject
    @IntAddOperationInterceptor
    lateinit var intBinaryOperator:IBinaryOperator<Int>

    private fun showDateTime()
    {
        StringBuilder().apply {
            append("DateTime:");append(dateTimeInfo.toString());append("\n")
            append("Date:");append(dateInfo.toString());append("\n")
            append("Time:");append(timeInfo.toString())
        }.also {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
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
        }else
            mBinding.result = "Invalid operation"
    }
}