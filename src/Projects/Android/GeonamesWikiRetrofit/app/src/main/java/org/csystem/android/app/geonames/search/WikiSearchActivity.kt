package org.csystem.android.app.geonames.search

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonames.search.databinding.ActivityWikiSearchBinding
import org.csystem.android.app.geonames.search.global.key.QUERY_LIST
import org.csystem.android.app.geonames.search.global.key.SETTINGS_INPUT
import org.csystem.android.app.geonames.search.global.key.USERNAME
import org.csystem.android.app.geonames.search.global.key.WIKI_INFO_DTO
import org.csystem.android.app.geonames.search.repositorylib.dal.GeonamesHelper
import org.csystem.android.app.geonames.search.repositorylib.dto.QuerySearchDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiSearchDBDTO
import org.csystem.android.app.geonames.search.viewmodel.listeners.WikiSearchActivityListenerViewModel
import org.csystem.android.app.geonames.search.wikisearch.api.IWikiSearchService
import org.csystem.android.app.geonames.search.wikisearch.api.entity.WikiInfo
import org.csystem.android.app.geonames.search.wikisearch.api.entity.WikiSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class WikiSearchActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityWikiSearchBinding
    private lateinit var mUsername: String
    private lateinit var mCountryCode: String
    private var mWikiInfoList: List<WikiInfo>? = null


    @Inject
    lateinit var wikiSearchService: IWikiSearchService

    @Inject
    lateinit var geonamesHelper: GeonamesHelper

    private fun initBoundBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wiki_search)
        mBinding.viewModel = WikiSearchActivityListenerViewModel(this)
        mBinding.wikiSearch = WikiSearchDBDTO()
        mBinding.wikiSearchArrayAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<WikiInfo>())


        loadData()
    }

    private fun loadData()
    {
        with(getSharedPreferences(SETTINGS_INPUT, MODE_PRIVATE)) {
            mUsername = getString(USERNAME, "")!!
            mCountryCode = "tr"
        }
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

    fun getButtonClicked()
    {
        if (mBinding.wikiSearch!!.q == "" || mBinding.wikiSearch!!.rowCount == 0) {
            Toast.makeText(this, "Please fill inputs", Toast.LENGTH_SHORT).show()
            return
        }

        mBinding.wikiSearchArrayAdapter!!.clear()

        if (geonamesHelper.existsWikiInfoByWikiSearchDTO(mBinding.wikiSearch!!)) {

            geonamesHelper.saveQuerySearch(QuerySearchDTO(mBinding.wikiSearch!!.q, "wiki_search", mBinding.wikiSearch!!.rowCount))

            mWikiInfoList = geonamesHelper.findWikiInfoByWikiSearchDTO(mBinding.wikiSearch!!)

            geonamesHelper.findWikiInfoByWikiSearchDTO(mBinding.wikiSearch!!).forEach {
                mBinding.wikiSearchArrayAdapter!!.add(it)
                mBinding.wikiSearchArrayAdapter!!.notifyDataSetChanged()
            }
        } else {
            val call = wikiSearchService.findByQ(
                mBinding.wikiSearch!!.q,
                mBinding.wikiSearch!!.rowCount,
                mUsername)
            call.enqueue(object : Callback<WikiSearch>
            {
                override fun onResponse(call: Call<WikiSearch>, response: Response<WikiSearch>)
                {
                    val wikiSearch = response.body()
                    if (wikiSearch?.wikiInfo != null) {
                        mWikiInfoList = wikiSearch.wikiInfo
                        wikiSearch.wikiInfo.forEach {
                            mBinding.wikiSearchArrayAdapter!!.add(it)
                            mBinding.wikiSearchArrayAdapter!!.notifyDataSetChanged()
                        }
                    } else
                        Toast.makeText(this@WikiSearchActivity,"Error in service",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<WikiSearch>, ex: Throwable)
                {
                    Toast.makeText(this@WikiSearchActivity,"Error occurred ${ex.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun closeButtonClicked()
    {
        finish()
    }

    fun wikiSearchItemClicked(pos: Int)
    {
        val selectedWikiInfo = mBinding.wikiSearchArrayAdapter!!.getItem(pos)!!
        Intent(this, WikiInfoActivity::class.java).apply {
            putExtra(WIKI_INFO_DTO, geonamesHelper.toWikiInfoDTO(selectedWikiInfo))
            startActivity(this)
        }
    }

    private fun saveCallback()
    {

        with(mWikiInfoList) {
            if (this != null) {
                geonamesHelper.saveWikiInfoList(mBinding.wikiSearch!!, this)
            } else
                Toast.makeText(this@WikiSearchActivity, "Error in save", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveButtonClicked()
    {
        if(mWikiInfoList == null){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show()
            return
        }

        AlertDialog.Builder(this)
            .setTitle(R.string.save_wiki_info_alert_dialog_title)
            .setMessage(R.string.save_wiki_info_alert_dialog_message)
            .setPositiveButton(R.string.save_wiki_info_alert_dialog_ok_text) { _, _ -> saveCallback() }
            .setNegativeButton(R.string.save_wiki_info_alert_dialog_no_text) { _, _ -> }.create()
            .show()
    }

    fun queryListButtonClicked()
    {
        Intent(this, QueryListActivity::class.java).apply {
            putExtra(QUERY_LIST, "wiki_search")
            startActivity(this)
        }
    }
}