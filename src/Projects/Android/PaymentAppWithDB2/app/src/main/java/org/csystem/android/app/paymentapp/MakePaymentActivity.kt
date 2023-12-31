package org.csystem.android.app.paymentapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.paymentapp.data.service.PaymentApplicationDataService
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO
import org.csystem.android.app.paymentapp.data.service.dto.PaymentSaveDTO
import org.csystem.android.app.paymentapp.databinding.ActivityMakePaymentBinding
import org.csystem.android.app.paymentapp.global.util.getLoginInfo
import org.csystem.android.app.paymentapp.viewmodel.MakePaymentActivityListenerViewModel
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class MakePaymentActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMakePaymentBinding

    private lateinit var mLoginInfo: LoginInfoDTO

    @Inject
    lateinit var dataService:PaymentApplicationDataService

    @Inject
    lateinit var threadPool:ScheduledExecutorService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_make_payment)
        mBinding.viewModel = MakePaymentActivityListenerViewModel(this)
        initLoginInfo()
        mBinding.paymentInfo = PaymentSaveDTO()
    }

    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
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

    private fun payButtonClickedCallback()
    {
        try {
            dataService.savePayment(mBinding.paymentInfo!!)
            runOnUiThread { Toast.makeText(this, "Paid successfully",Toast.LENGTH_SHORT).show() }
        }catch (ex:DataServiceException){
            runOnUiThread { Toast.makeText(this, "Data problem occurred!...",Toast.LENGTH_SHORT).show() }
            Log.d("data-service", ex.message!!)
        }catch (ex:Throwable){
            runOnUiThread { Toast.makeText(this, "Problem occurred!...",Toast.LENGTH_SHORT).show() }
            Log.d("any-problem", ex.message!!)
        }
    }

    fun payButtonClicked()
    {
        threadPool.execute { payButtonClickedCallback() }
    }

    fun clearButtonClicked()
    {
        for (i in 0..<mBinding.makePaymentActivityLinearLayoutMain.childCount){
            with(mBinding.makePaymentActivityLinearLayoutMain.getChildAt(i)){
                if(this is EditText)
                        text.clear()
            }
        }

    }

    fun closeButtonClicked()
    {
        finish()
    }
}