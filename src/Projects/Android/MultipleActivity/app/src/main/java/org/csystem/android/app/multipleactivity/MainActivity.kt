package org.csystem.android.app.multipleactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.activity.LoginActivity
import org.csystem.android.app.multipleactivity.databinding.ActivityMainBinding
import org.csystem.android.app.multipleactivity.viewmodel.MainActivityListenerViewModel

class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding

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
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    fun loginButtonClicked()
    {
//        startActivity(Intent(this, LoginActivity::class.java))
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun closeButtonClicked()
    {
        finish()
    }
}