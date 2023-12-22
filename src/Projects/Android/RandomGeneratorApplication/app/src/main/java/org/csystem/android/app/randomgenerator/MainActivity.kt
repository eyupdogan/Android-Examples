package org.csystem.android.app.randomgenerator

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.randomgenerator.databinding.ActivityMainBinding
import org.csystem.android.app.randomgenerator.global.what.WHAT_SCHEDULER_CALLBACK_EXCEPTION
import org.csystem.android.app.randomgenerator.global.what.WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION
import org.csystem.android.app.randomgenerator.global.what.WHAT_TEXT_ADAPTER_ADD
import org.csystem.android.app.randomgenerator.global.what.WHAT_WAIT_SCHEDULER_TIMEOUT
import org.csystem.android.app.randomgenerator.string.generateRandomTextEN
import org.csystem.android.app.randomgenerator.viewmodel.data.GenerateInfo
import org.csystem.android.app.randomgenerator.viewmodel.listener.MainActivityListenerViewModel
import java.lang.ref.WeakReference
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mScheduledFuture:ScheduledFuture<*>
    private lateinit var mHandler:Handler

    @Inject
    lateinit var executorService:ScheduledExecutorService

    private class SchedulerHandler(activity: MainActivity):Handler(Looper.myLooper()!!)
    {
        private val mWeakReference = WeakReference(activity)

        private fun handleSchedulerTrackCallbackException(str:String)
        {
            Toast.makeText(mWeakReference.get()!!, "Problem occurred in scheduler track thread $str", Toast.LENGTH_SHORT).show()
        }

        private fun handleSchedulerCallbackException(str:String)
        {
            Toast.makeText(mWeakReference.get()!!, "Problem occurred in scheduler: $str...", Toast.LENGTH_SHORT).show()
        }

        private fun handleSchedulerTimeout()
        {
            Toast.makeText(mWeakReference.get(), "Text(s) generated", Toast.LENGTH_SHORT).show()
        }

        private fun handleTextAdapterAdd(text:String)
        {
            mWeakReference.get()!!.mBinding.adapter!!.add(text)
        }

        override fun handleMessage(msg: Message)
        {
            when(msg.what){
                WHAT_TEXT_ADAPTER_ADD -> handleTextAdapterAdd(msg.obj as String)
                WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION -> handleSchedulerTrackCallbackException(msg.obj as String)
                WHAT_SCHEDULER_CALLBACK_EXCEPTION -> handleSchedulerCallbackException(msg.obj as String)
                WHAT_WAIT_SCHEDULER_TIMEOUT -> handleSchedulerTimeout()
            }
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModels()
    }

    private fun initViewModels()
    {
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.generateInfo = GenerateInfo()
        mBinding.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,ArrayList<String>())
        mBinding.enabled = true
    }

    private fun initialize()
    {
        initBinding()
        mHandler = SchedulerHandler(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun waitScheduler()
    {
        try {
            mScheduledFuture.get(mBinding.generateInfo!!.period * mBinding.generateInfo!!.count, TimeUnit.SECONDS)
        }catch (_:TimeoutException){
            mScheduledFuture.cancel(false)
            mHandler.sendEmptyMessage(WHAT_WAIT_SCHEDULER_TIMEOUT)
        }
    }

    private fun schedulerTrackCallback()
    {
        try {
            mBinding.enabled = false
            mScheduledFuture = executorService.scheduleAtFixedRate({schedulerCallback()},0L, mBinding.generateInfo!!.period, TimeUnit.SECONDS)

            waitScheduler()
        }catch (ex:Throwable){
//            runOnUiThread { Toast.makeText(this, "Problem occurred in scheduler track thread ${ex.message}", Toast.LENGTH_SHORT).show() }
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_SCHEDULER_TRACK_CALLBACK_EXCEPTION, ex.message))
        } finally {
            mBinding.enabled = true
        }

    }

    private fun schedulerCallback()
    {
        try {
//          runOnUiThread  {mBinding.adapter!!.add(generateRandomTextEN(Random.nextInt(mBinding.generateInfo!!.minimum, mBinding.generateInfo!!.maximum + 1)))}
            val count = Random.nextInt(mBinding.generateInfo!!.minimum, mBinding.generateInfo!!.maximum + 1)
            val text = generateRandomTextEN(count)
            mHandler.sendMessage(Message.obtain(mHandler, WHAT_TEXT_ADAPTER_ADD, text))
        }catch (ex:Throwable){
//            runOnUiThread { Toast.makeText(this, "Problem occurred in scheduler: ${ex.message}...", Toast.LENGTH_SHORT).show() }
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_SCHEDULER_CALLBACK_EXCEPTION, ex.message))
        }
    }

    fun generateButtonClicked()
    {
        mBinding.adapter!!.clear()
        executorService.execute { schedulerTrackCallback() }
    }

    fun saveButtonClicked()
    {

    }

    fun clearButtonClicked()
    {
        mBinding.adapter!!.clear()
    }
}