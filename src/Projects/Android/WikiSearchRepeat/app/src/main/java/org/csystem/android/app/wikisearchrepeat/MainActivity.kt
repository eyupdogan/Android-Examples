package org.csystem.android.app.wikisearchrepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.repositorylib.api.entity.WikiInfo
import org.csystem.android.app.wikisearchrepeat.databinding.ActivityMainBinding
import org.csystem.android.app.wikisearchrepeat.viewmodel.MainActivityListenerViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding
    private var mWikiInfoList:List<WikiInfo>? = null

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

    }

    fun listViewItemClicked(pos:Int)
    {

    }

    fun saveButtonClicked()
    {

    }
}