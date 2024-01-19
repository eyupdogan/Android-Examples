package org.csystem.android.app.payment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.data.service.PaymentApplicationDataService
import org.csystem.android.app.data.service.mapper.IUserMapper
import org.csystem.android.app.payment.databinding.ActivityMainBinding
import org.csystem.android.app.payment.viewmodel.MainActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
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
        Intent(this, RegisterActivity::class.java).apply { startActivity(this) }
    }
}