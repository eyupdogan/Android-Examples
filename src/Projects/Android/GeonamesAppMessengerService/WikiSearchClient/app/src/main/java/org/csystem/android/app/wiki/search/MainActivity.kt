package org.csystem.android.app.wiki.search

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.DeadObjectException
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.wiki.search.databinding.ActivityMainBinding
import org.csystem.android.app.wiki.search.viewmodel.data.WikiInfo
import org.csystem.android.app.wiki.search.viewmodel.listeners.MainActivityViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

const val SHARED_PREF_FILE_NAME = "geonames-input"
const val LAST_OPEN_BEFORE = "LAST_OPEN_BEFORE"
const val Q = "Q"
const val MAX_ROWS = "MAX_ROWS"

const val WIKI_SEARCH_SERVICE_ACTION_NAME = "org.csystem.app.service.geonames.search.WIKI"
const val WIKI_SEARCH_SERVICE_PACKAGE_NAME = "org.csystem.android.app.service.geonames.search"
const val WHAT_WIKI_SEARCH = 0

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityMainBinding

    private var mRequestMessenger:Messenger? = null
    private var mReplyMessenger:Messenger? = null
    private var mBound = false
    private val mWikiInfo =  ArrayList<WikiInfo>()

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter

    private val mWikiServiceConnection = object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?)
        {
            mRequestMessenger = Messenger(service)
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?)
        {
            mRequestMessenger = null
            mReplyMessenger = null
            mBound = false
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.wikiInfoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<WikiInfo>())
    }

    private fun loadData()
    {
        val sharedPref = getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE)
        val prefs = getPreferences(MODE_PRIVATE)

        mBinding.lastOpenBefore = "Last Open:${prefs.getString(LAST_OPEN_BEFORE, "")}"
        mBinding.q = sharedPref.getString(Q, "zonguldak")
        mBinding.maxRows = sharedPref.getInt(MAX_ROWS, 10)
    }

    private fun initBoundData()
    {
        initBinding()
        loadData()
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

    private fun bindWikiSearchService()
    {
        val intent = Intent(WIKI_SEARCH_SERVICE_ACTION_NAME).setPackage(WIKI_SEARCH_SERVICE_PACKAGE_NAME)
        if(!bindService(intent, mWikiServiceConnection, Context.BIND_AUTO_CREATE))
            throw RemoteException("Bind problem!...")
    }

    private fun unbindWikiSearchService()
    {
        if (mBound){
            unbindService(mWikiServiceConnection)
            mBound = false
            mRequestMessenger = null
            mReplyMessenger = null
        }
    }

    private fun sendData()
    {
        try {
//            val message = Message.obtain(null, WHAT_WIKI_SEARCH, mBinding.maxRows, 0, mBinding.q!!)
            val message = Message.obtain(null, WHAT_WIKI_SEARCH, mBinding.maxRows, 0)

            //message.replyTo = mReplyMessenger
            mRequestMessenger?.send(message)
        }catch (ex:DeadObjectException){
            Log.d("dead-object-ex", ex.message ?: "Dead object!!..")
            Toast.makeText(this, "Connection problem!...", Toast.LENGTH_LONG).show()
        }catch (ex: Throwable) {
            Log.d("send-ex", ex.message ?: "General problem occurred!!..")
            Toast.makeText(this, "Problem occurred!...", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart()
    {
        try {
            bindWikiSearchService()
        }catch (ex:RemoteException){
            Log.d("bind-service", ex.message ?: "Bind problem occurred!!..")
            Toast.makeText(this, "Remote problem!...", Toast.LENGTH_LONG).show()
        }catch (ex: Throwable) {
            Log.d("bind-service_ex", ex.message ?: "General problem occurred!!..")
            Toast.makeText(this, "Problem occurred!...", Toast.LENGTH_LONG).show()
        }
        super.onStart()
    }

    override fun onStop()
    {
        try {
            unbindWikiSearchService()
        }
        catch (ex: RemoteException) {
            Log.d("unbind-service", ex.message ?: "Unbind problem occurred!!..")
            Toast.makeText(this, "Remote problem!...", Toast.LENGTH_LONG).show()
        }
        catch (ex: Throwable) {
            Log.d("unbind-service_ex", ex.message ?: "General problem occurred!!..")
            Toast.makeText(this, "Problem occurred!...", Toast.LENGTH_LONG).show()
        }
        super.onStop()
    }

    override fun onDestroy()
    {
        with(getSharedPreferences(SHARED_PREF_FILE_NAME, MODE_PRIVATE).edit()){
            putString(Q, mBinding.q!!)
            putInt(MAX_ROWS, mBinding.maxRows)
            apply()
        }

        with(getPreferences(MODE_PRIVATE).edit()){
            putString(LAST_OPEN_BEFORE, dateTimeFormatter.format(LocalDateTime.now()))
            apply()
        }
        super.onDestroy()
    }

    fun searchButtonClicked()
    {
        try {
            if (mBound){
                sendData()
            }
        }catch (ex: Throwable) {
            Log.d("search-ex", ex.message ?: "General problem occurred!!..")
            Toast.makeText(this, "Problem occurred try again later!...", Toast.LENGTH_LONG).show()
        }
    }
}