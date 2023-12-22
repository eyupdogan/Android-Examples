package org.csystem.android.app.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.payment.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivitySplashBinding

    private fun initCountDownTimer()
    {
        object :CountDownTimer(3000, 1000){
            override fun onTick(remainingMillis: Long)
            {
                mBinding.counter = (remainingMillis / 1000L).toString()
            }

            override fun onFinish()
            {
                Intent(this@SplashActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }
        }.start()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    private fun initialize()
    {
        initBinding()
        initCountDownTimer()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}