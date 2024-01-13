package org.csystem.android.app.wikisearch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.wiki.search.R
import org.csystem.android.app.wiki.search.databinding.ActivityMainBinding
import org.csystem.android.app.wikisearch.viewmodel.MainActivityListenerViewModel
import org.csystem.android.app.wikisearch.viewmodel.WikiInfo
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{

    private lateinit var mBinding:ActivityMainBinding
    private val mWikiInfo:ArrayList<WikiInfo> = ArrayList()

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter








    private fun initBoundData()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.q = ""
        mBinding.maxRows = 10
    }

    private fun initialize()
    {
        initBoundData()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun searchButtonClicked()
    {
        Intent("android.intent.action.WIKI").apply {
            startActivityCallback(this)
        }
    }

    private fun startActivityCallback(intent: Intent)
    {
        with(intent){
            putExtra("Q", mBinding.q!!)
            putExtra("MAX_ROWS", mBinding.maxRows)
            startActivity(this)
        }
    }

}