package org.csystem.android.app.generator.randomtextgenerator

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.generator.randomtextgenerator.data.RandomTextGeneratorInfo
import org.csystem.android.app.generator.randomtextgenerator.global.RANDOM_TEXT_GENERATOR_INFO
import java.io.IOException
import java.lang.ref.WeakReference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import javax.inject.Inject

const val WHAT_EXCEPTION = -1
const val WHAT_IO_EXCEPTION = -2

@AndroidEntryPoint
class RandomGeneratorService : Service()
{
    private lateinit var mHandler:RandomGeneratorHandler

    @Inject
    lateinit var threadPool: ExecutorService

    @Inject
    lateinit var dateTime: LocalDateTime

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter


    private class RandomGeneratorHandler(service:RandomGeneratorService):Handler(Looper.myLooper()!!){

        private val mWeakReference = WeakReference(service)
        override fun handleMessage(msg: Message)
        {
            super.handleMessage(msg)
        }
    }

    private fun randomTextGeneratorInfoCallback(info:RandomTextGeneratorInfo)
    {
        try {

        }catch (ex:IOException){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_IO_EXCEPTION, ex.message))
        }catch (ex:Throwable){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_EXCEPTION, ex.message))
        }
    }

    private fun getRandomTextGeneratorInfo(intent: Intent?):RandomTextGeneratorInfo
    {
        return when{
            Build.VERSION.SDK_INT <33 -> intent?.getSerializableExtra(RANDOM_TEXT_GENERATOR_INFO) as RandomTextGeneratorInfo
            else -> intent?.getSerializableExtra(RANDOM_TEXT_GENERATOR_INFO, RandomTextGeneratorInfo::class.java)!!
        }
    }

    private fun initialize()
    {
        mHandler = RandomGeneratorHandler(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        initialize()
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()

        val info = when{
            Build.VERSION.SDK_INT <33 -> intent?.getSerializableExtra(RANDOM_TEXT_GENERATOR_INFO) as RandomTextGeneratorInfo
            else -> intent?.getSerializableExtra(RANDOM_TEXT_GENERATOR_INFO, RandomTextGeneratorInfo::class.java)
        }

        threadPool.execute { randomTextGeneratorInfoCallback(info!!) }

        //stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder
    {
        throw UnsupportedOperationException("Unsupported")
    }
}