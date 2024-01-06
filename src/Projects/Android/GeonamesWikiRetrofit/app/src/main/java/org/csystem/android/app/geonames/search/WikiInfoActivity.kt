package org.csystem.android.app.geonames.search

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonames.search.databinding.ActivityWikiInfoBinding
import org.csystem.android.app.geonames.search.global.key.WIKI_INFO_DTO
import org.csystem.android.app.geonames.search.repositorylib.dto.WikiInfoDTO
import org.csystem.android.app.geonames.search.viewmodel.listeners.WikiInfoListenerViewModel

class WikiInfoActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityWikiInfoBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wiki_info)
        mBinding.viewModel = WikiInfoListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    private fun getWikiInfoDTO()
    {
        mBinding.wikiInfo = when {
            Build.VERSION.SDK_INT < 33 -> intent.getSerializableExtra(WIKI_INFO_DTO) as WikiInfoDTO
            else -> intent.getSerializableExtra(WIKI_INFO_DTO, WikiInfoDTO::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
        getWikiInfoDTO()
    }
}