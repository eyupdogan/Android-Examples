package org.csystem.android.app.geonameswikisearchretrofit

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonameswikisearchretrofit.databinding.ActivityMainBinding
import org.csystem.android.app.geonameswikisearchretrofit.di.module.mapper.IWikiInfoMapper
import org.csystem.android.app.geonameswikisearchretrofit.global.key.WIKI_INFO
import org.csystem.android.app.geonameswikisearchretrofit.repository.api.IGeonamesWikiSearchService
import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo
import org.csystem.android.app.geonameswikisearchretrofit.repository.api.WikiSearch
import org.csystem.android.app.geonameswikisearchretrofit.repository.dao.IWikiInfoDao
import org.csystem.android.app.geonameswikisearchretrofit.viewmodel.MainActivityListenerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding
    private var mWikiInfoList:List<WikiInfo>? = null

    @Inject
    lateinit var wikiSearchService: IGeonamesWikiSearchService

    @Inject
    lateinit var mWikiInfo:IWikiInfoMapper

    @Inject
    lateinit var wikiInfoDao:IWikiInfoDao

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initData()
    }
    private fun initData()
    {
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.wikiInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<WikiInfo>())
        mBinding.q = "zonguldak"
        mBinding.maxRows = 10
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
        val call = wikiSearchService.findByQ(mBinding.q!!, mBinding.maxRows, "csystem")

        call.enqueue(object :Callback<WikiSearch>{
            override fun onResponse(call: Call<WikiSearch>, response: Response<WikiSearch>)
            {
                val wikiSearch = response.body()

                if (wikiSearch?.wikiInfo != null) {
                    mWikiInfoList = wikiSearch.wikiInfo
                    wikiSearch.wikiInfo.forEach { mBinding.wikiInfoAdapter!!.add(it) }
                    mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
                }
                else
                    Toast.makeText(this@MainActivity, "Error in service", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<WikiSearch>, ex: Throwable)
            {
                Toast.makeText(this@MainActivity, "Exception occurred:${ex.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun listViewItemClicked(pos:Int)
    {
        Intent(this, WikiInfoActivity::class.java).apply {
            putExtra(WIKI_INFO, mWikiInfo.toWikiInfoSaveDTO(mBinding.wikiInfoAdapter!!.getItem(pos)!!))
            startActivity(this)
        }
    }

    fun saveButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.save_wiki_info_alert_dialog_title)
            .setMessage(R.string.save_wiki_info_alert_dialog_message)
            .setPositiveButton(R.string.save_wiki_info_alert_dialog_ok_text) {_, _ -> saveWikiInfoList()}
            .setNegativeButton(R.string.save_wiki_info_alert_dialog_no_text) {_, _ -> }.create().show()
    }

    private fun saveWikiInfoList()
    {
        with(mWikiInfoList) {
            if (this != null)
            {
                this.forEach {
                    wikiInfoDao.save(it)
                }
            } else
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()

        }
    }
}