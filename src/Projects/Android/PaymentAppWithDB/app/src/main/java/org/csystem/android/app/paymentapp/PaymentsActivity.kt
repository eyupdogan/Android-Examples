package org.csystem.android.app.paymentapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO
import org.csystem.android.app.paymentapp.databinding.ActivityPaymentsBinding
import org.csystem.android.app.paymentapp.global.util.getLoginInfo
import org.csystem.android.app.paymentapp.viewmodel.PaymentsActivityListenerViewModel
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class PaymentsActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityPaymentsBinding
    private lateinit var mLoginInfo: LoginInfoDTO

    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

    @Inject
    lateinit var threadPool:ScheduledExecutorService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payments)
        mBinding.viewModel = PaymentsActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initLoginInfo()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun listPaymentsButtonClickedCallback()
    {

    }

    fun listPaymentsButtonClicked() = threadPool.execute { listPaymentsButtonClickedCallback()}

    fun closeButtonClicked()
    {
        finish()
    }
}