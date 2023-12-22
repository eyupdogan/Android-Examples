package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.activity.viewmodel.LoginInfo
import org.csystem.android.app.multipleactivity.databinding.ActivityPaymentBinding
import org.csystem.android.app.multipleactivity.keys.LOGIN_INFO
import org.csystem.android.app.multipleactivity.keys.PRODUCT_NAME
import org.csystem.android.app.multipleactivity.keys.TOTAL_PRICE
import org.csystem.android.app.multipleactivity.viewmodel.PaymentActivityListenerViewModel
import org.csystem.android.app.multipleactivity.viewmodel.PaymentInfo
import org.csystem.app.multipleactivity.library.databinding.converter.PaymentQuantityStringConverter
import org.csystem.app.multipleactivity.library.databinding.converter.PaymentUnitPriceStringConverter

class PaymentActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityPaymentBinding

    private fun initViewModels()
    {
        mBinding.viewModel = PaymentActivityListenerViewModel(this)
        mBinding.loginInfo = when{
            Build.VERSION.SDK_INT < 33 -> intent.getSerializableExtra(LOGIN_INFO) as LoginInfo
            else -> intent.getSerializableExtra(LOGIN_INFO, LoginInfo::class.java)
        }
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result = ""
        PaymentQuantityStringConverter.failStr = "Invalid quantity"
        PaymentUnitPriceStringConverter.failStr = "Invalid unit price"
    }
    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment)

    }
    private fun initialize()
    {
        initBinding()
        initViewModels()
    }

    private fun closeAlertDialogPositiveCallback()
    {
        if(mBinding.result!!.isNotEmpty())
            Intent().apply {
                putExtra(PRODUCT_NAME, mBinding.paymentInfo!!.name)
                putExtra(TOTAL_PRICE, mBinding.paymentInfo!!.total)
            setResult(RESULT_OK, this)
            }
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun checkFail():Boolean
    {
        with(StringBuilder()){
            if(PaymentQuantityStringConverter.fail)
            {
                append(PaymentQuantityStringConverter.failStr)
                append("\n")
            }
            if(PaymentUnitPriceStringConverter.fail)
                append(PaymentUnitPriceStringConverter.failStr)

            if(isNotEmpty())
                Toast.makeText(this@PaymentActivity, this, Toast.LENGTH_SHORT).show()
        }

        return PaymentQuantityStringConverter.fail || PaymentUnitPriceStringConverter.fail
    }

    fun payButtonClicked()
    {
        mBinding.result = ""
        if(checkFail())
            return

        val paymentInfo = mBinding.paymentInfo!!
        with(PaymentInfo(paymentInfo.name, paymentInfo.unitPrice, paymentInfo.quantity)){
            mBinding.result = this.toString()
        }
    }

    fun clearButtonClicked()
    {
        mBinding.paymentInfo = PaymentInfo()
        mBinding.result = ""
    }

    fun closeButtonClicked()
    {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.alert_dialog_close_title)
            setMessage(R.string.alert_dialog_close_message)
                setPositiveButton(R.string.alert_dialog_close_positive_button_text){_,_ -> closeAlertDialogPositiveCallback()}
                setNegativeButton(R.string.alert_dialog_close_negative_button_text){_,_ ->}
            create()
            show()
        }
    }

    fun exitButtonClicked()
    {

    }
}