package org.csystem.android.app.generator.random

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.generator.random.databinding.ActivityMainBinding
import org.csystem.android.app.generator.random.global.BOUND
import org.csystem.android.app.generator.random.global.COUNT
import org.csystem.android.app.generator.random.global.FILE_NAME
import org.csystem.android.app.generator.random.global.IS_ABLE_TO_CREATE_NEW_SERVICE
import org.csystem.android.app.generator.random.global.IS_CHECKED
import org.csystem.android.app.generator.random.global.MIN
import org.csystem.android.app.generator.random.global.RANDOM_TEXT_GENERATOR_INFO
import org.csystem.android.app.generator.random.viewmodel.data.RandomTextGeneratorInfo
import org.csystem.android.app.generator.random.viewmodel.listeners.MainActivityListenerViewModel


class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding

    private fun initBoundBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.randomTextGeneratorInfo = RandomTextGeneratorInfo("", 0,0,0)
        mBinding.isChecked = false
        loadData()
    }

    private fun loadData()
    {
        val pref = getPreferences(MODE_PRIVATE)
        mBinding.randomTextGeneratorInfo!!.fileName = pref.getString(FILE_NAME, "")!!
        mBinding.randomTextGeneratorInfo!!.count = pref.getInt(COUNT, 0)
        mBinding.randomTextGeneratorInfo!!.min = pref.getInt(MIN, 0)
        mBinding.randomTextGeneratorInfo!!.bound = pref.getInt(BOUND, 0)
        mBinding.isChecked = pref.getBoolean(IS_CHECKED, false)
    }

    private fun initialize()
    {
        initBoundBinding()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onDestroy()
    {
        editPreferences()
        super.onDestroy()
    }

    private fun editPreferences()
    {
        with(getPreferences(MODE_PRIVATE).edit()) {
            putString(FILE_NAME, mBinding.randomTextGeneratorInfo!!.fileName)
            putInt(COUNT, mBinding.randomTextGeneratorInfo!!.count)
            putInt(MIN, mBinding.randomTextGeneratorInfo!!.min)
            putInt(BOUND, mBinding.randomTextGeneratorInfo!!.bound)
            putBoolean(IS_CHECKED, mBinding.isChecked!!)
            apply()
        }
    }


    fun startServiceButtonClicked()
    {
        Intent(this, RandomGeneratorService::class.java).apply {
            putExtra(RANDOM_TEXT_GENERATOR_INFO, mBinding.randomTextGeneratorInfo)
            putExtra(IS_ABLE_TO_CREATE_NEW_SERVICE, mBinding.isChecked)
            startService(this)
        }
        finish()
    }

    fun createNewServiceSwitchButtonClicked(isChecked:Boolean)
    {
        mBinding.isChecked = isChecked
    }
}