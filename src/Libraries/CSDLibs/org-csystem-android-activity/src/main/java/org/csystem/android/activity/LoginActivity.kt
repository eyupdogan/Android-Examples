package org.csystem.android.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.activity.databinding.ActivityLoginBinding
import org.csystem.android.activity.viewmodel.LoginActivityListenersViewModel
import org.csystem.android.activity.viewmodel.LoginInfo

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var mLauncher: ActivityResultLauncher<Intent>

    private fun paymentActivityCallback(result: ActivityResult)
    {

    }
    private fun initPaymentActivityResultLauncher() // bunu activity display edilmeden önce yapmamız gerekiyor (onStart/onCreate)
    {
        mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            paymentActivityCallback(it)
        }
    }
    private fun initViewModels() {
        mBinding.viewModel = LoginActivityListenersViewModel(this)
        mBinding.loginInfo = LoginInfo()
    }

    private fun initialize() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initViewModels()
        initPaymentActivityResultLauncher()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun loginButtonClicked()
    {
        Toast.makeText(this, "login button clicked", Toast.LENGTH_LONG).show()
    }
}