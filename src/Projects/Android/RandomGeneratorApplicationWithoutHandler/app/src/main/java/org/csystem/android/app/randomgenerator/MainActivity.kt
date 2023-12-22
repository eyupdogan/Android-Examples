package org.csystem.android.app.randomgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.randomgenerator.databinding.ActivityMainBinding
import org.csystem.android.app.randomgenerator.string.generateRandomTextEN
import org.csystem.android.app.randomgenerator.viewmodel.data.GenerateInfo
import org.csystem.android.app.randomgenerator.viewmodel.listener.MainActivityListenerViewModel
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mScheduledFuture:ScheduledFuture<*>

    @Inject
    lateinit var executorService:ScheduledExecutorService

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
        }
    }

    private fun schedulerTrackCallback()
    {
        try {
            mBinding.enabled = false
            mScheduledFuture = executorService.scheduleAtFixedRate({schedulerCallback()},0L, mBinding.generateInfo!!.period, TimeUnit.SECONDS)

            waitScheduler()
        }catch (ex:Throwable){
            runOnUiThread { Toast.makeText(this, "Problem occurred in scheduler track thread ${ex.message}", Toast.LENGTH_SHORT).show() }
        } finally {
            mBinding.enabled = true
        }

    }

    private fun schedulerCallback()
    {
        try {
            runOnUiThread  {mBinding.adapter!!.add(generateRandomTextEN(Random.nextInt(mBinding.generateInfo!!.minimum, mBinding.generateInfo!!.maximum + 1)))}
        }catch (ex:Throwable){
            runOnUiThread { Toast.makeText(this, "Problem occurred in scheduler: ${ex.message}...", Toast.LENGTH_SHORT).show() }
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