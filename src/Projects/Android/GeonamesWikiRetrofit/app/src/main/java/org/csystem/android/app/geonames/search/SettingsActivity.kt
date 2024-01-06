package org.csystem.android.app.geonames.search

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonames.search.global.key.COUNTRY_CODE
import org.csystem.android.app.geonames.search.global.key.SETTINGS_INPUT
import org.csystem.android.app.geonames.search.global.key.USERNAME
import org.csystem.android.app.geonames.search.databinding.ActivitySettingsBinding
import org.csystem.android.app.geonames.search.viewmodel.listeners.SettingsActivityListenerViewModel


class SettingsActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivitySettingsBinding

    private fun loadData()
    {
        mBinding.viewModel = SettingsActivityListenerViewModel(this)
        with(getSharedPreferences(SETTINGS_INPUT, MODE_PRIVATE)){
            mBinding.username = getString(USERNAME, "")
            mBinding.countryCodePos = getInt(COUNTRY_CODE, 0)
        }
    }

    private fun initBoundData()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        loadData()
    }


    private fun initCountryCodeAdapter()
    {
        mBinding.countryCodeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.spinner_country_codes)
        )
    }

    private fun initialize()
    {
        initBoundData()
        initCountryCodeAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun saveAndBack()
    {
        editSharedPrefs()
        finish()
    }

    private fun editSharedPrefs()
    {
        getSharedPreferences(SETTINGS_INPUT, MODE_PRIVATE).edit().apply{
            putString(USERNAME, mBinding.username)
            putInt(COUNTRY_CODE, mBinding.countryCodePos)
            apply()
        }
    }

    override fun onDestroy()
    {
        editSharedPrefs()
        super.onDestroy()
    }
}