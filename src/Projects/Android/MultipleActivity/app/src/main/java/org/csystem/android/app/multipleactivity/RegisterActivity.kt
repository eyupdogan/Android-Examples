package org.csystem.android.app.multipleactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.multipleactivity.databinding.ActivityRegisterBinding
import org.csystem.android.app.multipleactivity.viewmodel.RegisterActivityListenerViewModel
import org.csystem.android.app.multipleactivity.viewmodel.RegisterInfo

class RegisterActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityRegisterBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = RegisterActivityListenerViewModel(this)
        mBinding.registerInfo = RegisterInfo()
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

    fun registerButtonClicked()
    {

    }

    fun clearButtonClicked()
    {

    }

    fun closeButtonClicked()
    {
        finish()
    }
}