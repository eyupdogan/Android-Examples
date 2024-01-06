package org.csystem.android.app.geonames.search

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonames.search.databinding.ActivityPostalCodeInfoBinding
import org.csystem.android.app.geonames.search.global.key.POSTAL_CODE_INFO_DTO
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeDTO
import org.csystem.android.app.geonames.search.viewmodel.listeners.PostalCodeInfoListenerViewModel

class PostalCodeInfoActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityPostalCodeInfoBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_postal_code_info)
        mBinding.viewModel = PostalCodeInfoListenerViewModel(this)
    }

    private fun initPostalCode()
    {
        mBinding.postalCode = when {
            Build.VERSION.SDK_INT < 33 -> intent.getSerializableExtra(POSTAL_CODE_INFO_DTO) as PostalCodeDTO
            else -> intent.getSerializableExtra(POSTAL_CODE_INFO_DTO, PostalCodeDTO::class.java)
        }
    }

    private fun initialize()
    {
        initBinding()
        initPostalCode()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}