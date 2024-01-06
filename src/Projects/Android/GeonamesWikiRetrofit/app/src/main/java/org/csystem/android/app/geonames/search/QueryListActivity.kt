package org.csystem.android.app.geonames.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonames.search.databinding.ActivityQueryListBinding
import org.csystem.android.app.geonames.search.global.key.QUERY_LIST
import org.csystem.android.app.geonames.search.repositorylib.dal.GeonamesHelper
import org.csystem.android.app.geonames.search.viewmodel.listeners.QueryListActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class QueryListActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityQueryListBinding

    @Inject
    lateinit var geonamesHelper: GeonamesHelper

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_query_list)
        mBinding.viewModel = QueryListActivityListenerViewModel(this)
    }

    private fun initQuerySearchAdapter()
    {
        val queryType = intent.getStringExtra(QUERY_LIST) ?: ""
        val querySearchList = geonamesHelper.getQuerySearchList(queryType)
        mBinding.querySearchAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList(querySearchList))
    }

    private fun initialize()
    {
        initBinding()
        initQuerySearchAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}