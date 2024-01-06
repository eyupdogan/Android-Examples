package org.csystem.android.app.geonames.search

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonames.search.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivitySplashBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    private fun initialize()
    {
        initBinding()
        initCountDownTimer()
    }

    private fun initCountDownTimer()
    {
        object :CountDownTimer(3000, 1000){
            override fun onTick(millisUntilFinished: Long)
            {
                mBinding.counter = (millisUntilFinished / 1000L).toString()
            }

            override fun onFinish()
            {
                Intent(this@SplashActivity, MainActivity::class.java).apply { startActivity(this) }
                finish()
            }
        }.start()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}