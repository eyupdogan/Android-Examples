package org.csystem.android.app.displaydatetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.displaydatetime.databinding.ActivityMainBinding
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mTimer:Timer

    private fun createTimerTask() = object :TimerTask(){
        override fun run()
        {
         mBinding.dateTime = mFormatter.format(LocalDateTime.now())
        }
    }

    @Inject
    @LocalDateTimeFormatterInterceptor
    lateinit var mFormatter:DateTimeFormatter

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initialize()
    {
        initBinding()
        mTimer = Timer()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }


    override fun onResume()
    {
        super.onResume()
        // Uygulama burada kilitlendi. Activity yok olamıyor, uygulama geri tuşu üzerinden geri gelemiyor,
        // onDestroy a akış gidemiyor, sistemden sisteme değişse de belli bir zaman sonra "isn't responding"
        // uyarısı çıkıyor
        while (true) {
           mBinding.dateTime = mFormatter.format(LocalDateTime.now())
           Thread.sleep(1000)
        }

        // onResume içerisinde Timer tetiklendi ve onResume den çıkıldı
        // mTimer.scheduleAtFixedRate(createTimerTask(), 0, 1000)

    }
}