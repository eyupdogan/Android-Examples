package org.csystem.android.app.generator.random

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.generator.random.global.IS_ABLE_TO_CREATE_NEW_SERVICE
import org.csystem.android.app.generator.random.global.RANDOM_TEXT_GENERATOR_INFO
import org.csystem.android.app.generator.random.global.WHAT_EXCEPTION
import org.csystem.android.app.generator.random.global.WHAT_IO_EXCEPTION
import org.csystem.android.app.generator.random.global.WHAT_TEXT_DISPLAY
import org.csystem.android.app.generator.random.viewmodel.data.RandomTextGeneratorInfo
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.ref.WeakReference
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import javax.inject.Inject
import kotlin.random.Random


@AndroidEntryPoint
class RandomGeneratorService : Service()
{
    private lateinit var mHandler:RandomGeneratorHandler
    private var mIsAbleToCreateNewService:Boolean? = null

    @Inject
    lateinit var threadPool:ExecutorService

    @Inject
    lateinit var dateTime: LocalDateTime

    @Inject
    lateinit var formatter:DateTimeFormatter



    private class RandomGeneratorHandler(service:RandomGeneratorService):Handler(Looper.myLooper()!!){

        private val mWeakReference = WeakReference(service)
        override fun handleMessage(msg: Message)
        {
            val service = mWeakReference.get()!!
            when(msg.what){
                WHAT_TEXT_DISPLAY -> Toast.makeText(service, msg.obj.toString(), Toast.LENGTH_SHORT).show()
                WHAT_IO_EXCEPTION -> Toast.makeText(service, "IO problem:${msg.obj}", Toast.LENGTH_SHORT).show()
                WHAT_EXCEPTION -> Toast.makeText(service, "problem:${msg.obj}", Toast.LENGTH_SHORT).show()
            }
            super.handleMessage(msg)
        }
    }

    private fun createRandomText(count:Int, random: Random = Random):String
    {
        val sb = StringBuilder(count)
        generateSequence(0) {it + 1}.takeWhile { it <= count }
            .forEach {_ -> sb.append((if (random.nextBoolean()) 'A' else 'a') + random.nextInt(26))  }



        return sb.toString()
    }

    private fun randomGeneratorOutputForEachCallback(info: RandomTextGeneratorInfo, bw:BufferedWriter)
    {
        try {
            val generatedText = "${createRandomText(Random.nextInt(info.min, info.bound))} ${formatter.format(dateTime)}"
            bw.run {write(generatedText); newLine();flush() }
            Thread.sleep(Random.nextLong(300, 1001))
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_TEXT_DISPLAY, generatedText))
        }catch (ex:IOException){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_IO_EXCEPTION, ex.message))
        }catch (ex:Throwable){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_EXCEPTION, ex.message))
        }

    }

    private fun randomGeneratorOutputCallback(info: RandomTextGeneratorInfo, bw:BufferedWriter)
    {
        generateSequence(0) {it + 1}.takeWhile { it <= info.count }
            .forEach { _ -> randomGeneratorOutputForEachCallback(info, bw)}
    }

    private fun randomGeneratorCallback(info: RandomTextGeneratorInfo)
    {
        try {
            // dosya var mı yok mu kontrolu yapıldı varsayıyoruz
            openFileOutput(info.fileName, MODE_APPEND)
                .use { BufferedWriter(OutputStreamWriter(it, StandardCharsets.UTF_8)).apply{ randomGeneratorOutputCallback(info, this)} }

            if (!mIsAbleToCreateNewService!!){
                Toast.makeText(this, "İkinci service yaratılamıyor", Toast.LENGTH_SHORT).show()
                stopSelf()
            }
        }catch (ex:IOException){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_IO_EXCEPTION, ex.message))
        }catch (ex:Throwable){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_EXCEPTION, ex.message))
        }
    }

    private fun initialize(intent: Intent?)
    {
        mHandler = RandomGeneratorHandler(this)
        mIsAbleToCreateNewService = intent?.getBooleanExtra(IS_ABLE_TO_CREATE_NEW_SERVICE, false)!!
        threadPool.execute { randomGeneratorCallback(getRandomTextGeneratorInfo(intent)) }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()
        initialize(intent)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun getRandomTextGeneratorInfo(intent: Intent?): RandomTextGeneratorInfo
    {
        return when
        {
            Build.VERSION.SDK_INT < 33 -> intent?.getSerializableExtra(RANDOM_TEXT_GENERATOR_INFO) as RandomTextGeneratorInfo
            else -> intent?.getSerializableExtra(
                RANDOM_TEXT_GENERATOR_INFO,
                RandomTextGeneratorInfo::class.java
            )
        }!!
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