package org.csystem.android.app.geonames.search

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.geonames.search.global.key.SETTINGS_INPUT
import org.csystem.android.app.geonames.search.global.key.USERNAME
import org.csystem.android.app.geonames.search.databinding.ActivityMainBinding
import org.csystem.android.app.geonames.search.viewmodel.listeners.MainActivityListenerViewModel

class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding


    private fun loadSharedData()
    {
        with(getSharedPreferences(SETTINGS_INPUT, MODE_PRIVATE)){
            val username = getString(USERNAME, "")
            mBinding.enableSearch = username!!.isNotEmpty()
        }
    }

    private fun initBoundData()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBoundData()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    override fun onResume()
    {
        loadSharedData()
        super.onResume()
    }

    fun settingsButtonClicked()
    {
        Intent(this, SettingsActivity::class.java).apply { startActivity(this) }
    }

    fun wikiSearchButtonClicked()
    {
        startActivity(Intent(this, WikiSearchActivity::class.java))
    }

    fun postalCodeButtonClicked()
    {
        startActivity(Intent(this, PostalCodeActivity::class.java))
    }

    fun closeButtonClicked()
    {
        finish()
    }
}