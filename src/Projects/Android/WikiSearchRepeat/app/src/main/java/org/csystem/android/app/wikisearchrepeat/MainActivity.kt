package org.csystem.android.app.wikisearchrepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.repositorylib.api.IWikiSearchService
import org.csystem.android.app.repositorylib.api.entity.WikiInfo
import org.csystem.android.app.repositorylib.api.entity.WikiSearch
import org.csystem.android.app.wikisearchrepeat.databinding.ActivityMainBinding
import org.csystem.android.app.wikisearchrepeat.viewmodel.MainActivityListenerViewModel
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding
    private var mWikiInfoList:List<WikiInfo>? = null

    @Inject
    lateinit var wikiSearchService: IWikiSearchService

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

                if (wikiSearch?.wikiInfo != null){
                    wikiSearch.wikiInfo.forEach { mBinding.wikiInfoAdapter!!.add(it) }
                    mBinding.wikiInfoAdapter!!.notifyDataSetChanged()
                }else
                    Toast.makeText(this@MainActivity, "Error in service", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<WikiSearch>, ex: Throwable)
            {
                Toast.makeText(this@MainActivity, "Exception occurs ${ex.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun listViewItemClicked(pos:Int)
    {

    }

    fun saveButtonClicked()
    {

    }
}