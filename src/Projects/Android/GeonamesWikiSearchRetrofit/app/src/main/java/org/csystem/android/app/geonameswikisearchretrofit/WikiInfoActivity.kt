package org.csystem.android.app.geonameswikisearchretrofit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonameswikisearchretrofit.databinding.ActivityWikiInfoBinding
import org.csystem.android.app.geonameswikisearchretrofit.global.key.WIKI_INFO
import org.csystem.android.app.geonameswikisearchretrofit.viewmodel.data.WikiInfoSaveDTO

class WikiInfoActivity : AppCompatActivity()
{
    lateinit var mBinding:ActivityWikiInfoBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wiki_info)
    }

    private fun initData()
    {
        mBinding.wikiInfo = when{
            Build.VERSION.SDK_INT < 33 -> intent.getSerializableExtra(WIKI_INFO) as WikiInfoSaveDTO
            else -> intent.getSerializableExtra(WIKI_INFO, WikiInfoSaveDTO::class.java)
        }
    }

    private fun initialize()
    {
        initBinding()
        initData()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}