/*--------------------------------------------------------------------------------------------------
    Gerçekten de multipleActivity de bizim yazmış olduğumuz login sayfası açıldı ve loginButton a
    tıklayınca Toast mesajını gördük
--------------------------------------------------------------------------------------------------*/
// LoginActivity
package org.csystem.android.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.activity.databinding.ActivityLoginBinding
import org.csystem.android.activity.viewmodel.LoginActivityListenerViewModel
import org.csystem.android.activity.viewmodel.LoginInfo

class LoginActivity : AppCompatActivity()
{
    lateinit var mBinding:ActivityLoginBinding
    lateinit var mLauncher: ActivityResultLauncher<Intent>

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private fun initViewModels()
    {
        mBinding.viewModel = LoginActivityListenerViewModel(this)
        mBinding.loginInfo = LoginInfo()
    }

    private fun initialize()
    {
        initBinding()
        initViewModels()
    }

    private fun paymentActivityCallback()
    {

    }

    private fun initPaymentActivityResultLauncher()
    {
        mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {paymentActivityCallback()}
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun loginButtonClicked()
    {
        Toast.makeText(this, "login button clicked", Toast.LENGTH_SHORT).show()
    }

    fun closeButtonClicked()
    {

    }
}