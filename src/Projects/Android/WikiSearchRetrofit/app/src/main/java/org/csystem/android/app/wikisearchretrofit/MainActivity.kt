package org.csystem.android.app.wikisearchretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.wikisearchretrofit.api.IGeonamesWikiSearchService
import org.csystem.android.app.wikisearchretrofit.api.WikiInfo
import org.csystem.android.app.wikisearchretrofit.api.WikiSearch
import org.csystem.android.app.wikisearchretrofit.databinding.ActivityMainBinding
import org.csystem.android.app.wikisearchretrofit.viewmodel.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import javax.inject.Inject

const val SHARED_PREF_FILE_NAME = "geonames-input"
const val LAST_OPEN_BEFORE = "LAST_OPEN_BEFORE"
const val Q = "Q"
const val MAX_ROWS = "MAX_ROWS"

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    lateinit var mBinding:ActivityMainBinding
    private val mWikiInfo:ArrayList<WikiInfo> = ArrayList()

    @Inject
    lateinit var wikiSearchService:IGeonamesWikiSearchService

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter

    @Inject
    lateinit var threadPool:ExecutorService

    private fun saveCacheCallback(bw:BufferedWriter)
    {
        for (i in 1..<mWikiInfo.size)
            saveData(mWikiInfo[i].summary, bw)
    }

    private fun saveData(data:String?, bw: BufferedWriter)
    {
        try {
            bw.write("$data\r\n")
        }catch (ex:IOException){
            runOnUiThread { Toast.makeText(this, "IO Error while saving:${ex.message}", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun saveCache()
    {
        try {
           BufferedWriter(OutputStreamWriter(FileOutputStream(File(cacheDir, "data.dat")))).use(this::saveCacheCallback)
        }catch (ex:IOException){
            runOnUiThread { Toast.makeText(this, "IO Error while openning:${ex.message}", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun loadData()
    {
        with(getPreferences(MODE_PRIVATE)){
            mBinding.lastOpenBefore = getString(LAST_OPEN_BEFORE, "")
        }

        with(getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE)){
            mBinding.q = getString(Q, "zonguldak")
            mBinding.maxRows = getInt(MAX_ROWS,10)
        }
    }

    private fun initBoundData()
    {
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.wikiInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mWikiInfo)
        loadData()
    }
    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initBoundData()
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

    fun getButtonClicked()
    {
        mBinding.wikiInfoAdapter!!.clear()
        val call = wikiSearchService.findByQ(mBinding.q!!, mBinding.maxRows!!, "csystem")

        call.enqueue(object :Callback<WikiSearch>{
            override fun onResponse(call: Call<WikiSearch>, response: Response<WikiSearch>)
            {
                val wikiSearch = response.body()

                if(wikiSearch?.wikiInfo != null){
                    wikiSearch.wikiInfo.forEach { mBinding.wikiInfoAdapter!!.add(it) }
                    mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
                    threadPool.execute { saveCache() }
                }else
                    Toast.makeText(this@MainActivity, "Error in service", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<WikiSearch>, ex: Throwable)
            {
                Toast.makeText(this@MainActivity, "Error occurred:${ex.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy()
    {
        getPreferences(MODE_PRIVATE).edit().apply {
            putString(LAST_OPEN_BEFORE, dateTimeFormatter.format(LocalDateTime.now()))
            apply()
        }

        getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE).edit().apply {
            putString(Q, mBinding.q)
            putInt(MAX_ROWS, mBinding.maxRows)
            apply()
        }
        super.onDestroy()
    }
}