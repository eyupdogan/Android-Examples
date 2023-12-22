/*--------------------------------------------------------------------------------------------------
    Daha önce yapılan android uygulamasında onStop ta interrupt() metodunun çağırılma sebebi Thread
    sınıfının interrupt metodu interrupt flag değerini set eder yani mantıksal true değerine çeker.
    Daha önce de bahsedildiği gibi sleep ve join metodları interrupt flag değerine duyarlı oldukları
    için de  aşağıdaki clockThreadCallback() metodu içerisindeki sonsuz döngüde bulunan sleep()
    'Thread.sleep(1000)' metodu flag değerinin set edildiğini algıladığı için de InterruptedException
    oluşur ve akış catch bloğuna geçer ve thread sonlanır
--------------------------------------------------------------------------------------------------*/

package org.csystem.android.app.displaydatetime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.displaydatetime.databinding.ActivityMainBinding
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mTimerDateTime:Timer
    private lateinit var mChronoTimer:Timer
    private lateinit var mClockThread:Thread

    @Inject
    @LocalDateTimeFormatterInterceptor
    lateinit var dateTimeFormatter:DateTimeFormatter

    @Inject
    @LocalTimeFormatterInterceptor
    lateinit var timeFormatter:DateTimeFormatter

    private fun createChronoTimerTask() :TimerTask
    {
        var seconds = 0L
        return object :TimerTask()
        {
            override fun run()
            {
                displayChronoDuration(seconds++)
            }
        }
    }


    private fun displayChronoDuration(seconds:Long)
    {
        val hour = seconds / 60 / 60
        val minute = seconds / 60 % 60
        val second = seconds % 60

        mBinding.chronometer = "%02d:%02d:%02d".format(hour, minute, second)
    }

    private fun createDateTimeTimerTask() = object : TimerTask()
    {
        override fun run()
        {
            mBinding.dateTime = dateTimeFormatter.format(LocalDateTime.now())
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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

    private fun scheduleChronoTimer()
    {
        mChronoTimer = Timer()
        mChronoTimer.scheduleAtFixedRate(createChronoTimerTask(), 0, 1000)
    }

    private fun scheduleDateTimeTimer()
    {
        mTimerDateTime = Timer()
        mTimerDateTime.scheduleAtFixedRate(createDateTimeTimerTask(), 0, 1000)
    }

    // xml de Chronometer widgeti ve viewBinding kullanıldı
    private fun startAutoDisplayChronometer()
    {
        mBinding.mainActivityChronometerAutoDisplay.start()
    }

    private fun stopAutoDisplayChronometer()
    {
        mBinding.mainActivityChronometerAutoDisplay.stop()
    }



    // Thread kullanıldı.Timer kullanımı daha uygundur thread kullanımını da göstermek üzere yazılmıştır
    private fun clockThreadCallback()
    {
        try
        {
            while (true){


                //mBinding.mainActivityTextViewTime.text = timeFormatter.format(LocalTime.now())
                // yukarıdaki kod uygulamayı çökertir. Sebebi ise backgroundThread da çalışan bu thread
                // UI thread i olan mainThread i değiştirmeye çalışıyor
                // bazı sistemlerde çalışabilir ancak bizim sorunumuz backgroundThread te UI işleminin
                // yapılmaya çalışılması
                mBinding.time = timeFormatter.format(LocalTime.now())
                Thread.sleep(1000)
            }
        }catch (ignore:InterruptedException){
            //Toast.makeText(this, "Artık sonlanmak lazım", Toast.LENGTH_LONG).show() ui i direkt
            // değiştirmeye çalıştığı için uygulama çöker
            runOnUiThread { Toast.makeText(this, "Artık sonlanmak lazım", Toast.LENGTH_LONG).show() }
        }
    }

    private fun startClock() // thread kullanıldı
    {
        mClockThread = thread { clockThreadCallback()}
    }

    // bir timer nesnesi ile 1 kez schedule yapılabildiği için yeniden yaratılsın diye timer ı
    // onStart a koyduk
    override fun onStart()
    {
        try {
            startAutoDisplayChronometer() // xml chronometer widget i
            scheduleChronoTimer() // timer() kullanıldı
            scheduleDateTimeTimer() // timer() kullanıldı
            startClock() // thread kullanıldı
            super.onStart()
        }catch (ex:Throwable){
            Log.d("on-start", ex.message!!)
            Toast.makeText(this, "problem occurred onStart!", Toast.LENGTH_SHORT).show()
        }
    }



    // activity nin görünürlüğü gittiği zaman boşu boşuna arka planda çalışmasın diye onStop ta durdurduk
    override fun onStop()
    {
        try {
            stopAutoDisplayChronometer()
            mChronoTimer.cancel()
            mTimerDateTime.cancel()
            mClockThread.interrupt() // ayrıntılı olarak ele alınacak
            super.onStop()
        }catch (ex:Throwable){
            Log.d("on-stop", ex.message!!)
            Toast.makeText(this, "problem occurred onStop!", Toast.LENGTH_SHORT).show()
        }
    }
}