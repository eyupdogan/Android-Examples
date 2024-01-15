package org.csystem.android.app.wikisearch

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.wiki.search.R
import org.csystem.android.app.wiki.search.databinding.ActivityMainBinding
import org.csystem.android.app.wikisearch.viewmodel.MainActivityListenerViewModel
import org.csystem.android.app.wikisearch.viewmodel.WikiInfo
import org.csystem.android.app.wikisearch.viewmodel.WikiSearch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

const val WIKI_INFO_LIST = "WIKI_INFO_LIST"

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{

    private lateinit var mBinding:ActivityMainBinding
    private lateinit var mLauncher:ActivityResultLauncher<Intent>

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter

    private fun initBoundData()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.q = ""
        mBinding.maxRows = 10
        mBinding.wikiInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<WikiInfo>())
    }

    private fun initialize()
    {
        initBoundData()
        initMainActivityResultLauncher()
    }

    private fun mainActivityCallback(result:ActivityResult)
    {

        if (result.resultCode != RESULT_OK)
            return

        Toast.makeText(this, "mainActivityCallback", Toast.LENGTH_SHORT).show()

        val data = result.data

        val wikiInfoList = when {
            Build.VERSION.SDK_INT < 33 -> data?.getSerializableExtra(WIKI_INFO_LIST) as WikiSearch
            else -> data?.getSerializableExtra(WIKI_INFO_LIST, WikiSearch::class.java)
        }

        Toast.makeText(this, "mainActivityCallback", Toast.LENGTH_SHORT).show()

        Log.d("denemexxxx", wikiInfoList?.wikiInfo?.toString() ?:   "kvzkdc")

        wikiInfoList?.wikiInfo?.forEach {
            runOnUiThread {
                mBinding.wikiInfoAdapter!!.add(it)
                mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun initMainActivityResultLauncher()
    {
        Toast.makeText(this, "initMainActivityResultLauncher,", Toast.LENGTH_SHORT).show()
        mLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            mainActivityCallback(it)
        }
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
            mLauncher.launch(this)
        }
    }

}