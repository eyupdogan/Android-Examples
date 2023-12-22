/*--------------------------------------------------------------------------------------------------
    splashActivity oluşturuldu, ancak splashActivity oluşturmanın başka yolları da var. Bir adet
    uyarı çıkıyor @SuppressLint("CustomSplashScreen") ile işaretlersek uyarı kalkıyor
    Bu activity nin ilk açılan activity olmasını istediğimiz için androidManifest dosyasında intent
    filter ı splash içine almam ve exported ını da true yapmam gerekiyor
----------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.displaydatetime

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.displaydatetime.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivitySplashBinding

    private fun initCountDownTimer()
    {
        // Main Thread te çalışıyor, klasik Timer lar background thread da çalışıyordu!!!
        object : CountDownTimer(10000, 1000){
            // onTick her countDownInterval de (ki yukarıda 1000 ms verildi) bir çalışıyor
            override fun onTick(remainingMillis: Long)
            {
                mBinding.counter = (remainingMillis / 1000L).toString()
            }
            // onFinish millisInFuture (ki yukarıda 10000 ms verildi) bitince yapılacak işi gerçekleştiriyor
            override fun onFinish()
            {
                finish()
                Intent(this@SplashActivity, MainActivity::class.java).apply {
                    startActivity(this)
                }
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