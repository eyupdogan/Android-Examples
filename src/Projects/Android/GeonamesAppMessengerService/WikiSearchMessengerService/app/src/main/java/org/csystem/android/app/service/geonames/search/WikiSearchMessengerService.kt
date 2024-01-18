package org.csystem.android.app.service.geonames.search

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.service.geonames.search.api.IGeonamesWikiSearchService
import org.csystem.android.app.service.geonames.search.api.WikiSearch
import org.csystem.android.library.service.search.wiki.common.Common
import retrofit2.Call
import retrofit2.Response
import java.lang.ref.WeakReference
import java.time.format.DateTimeFormatter
import java.util.concurrent.ExecutorService
import javax.inject.Inject



@AndroidEntryPoint
class WikiSearchMessengerService : Service()
{
    private lateinit var mMessenger:Messenger
    private lateinit var mHandler:Handler
    @Inject
    lateinit var wikiSearchService: IGeonamesWikiSearchService

    @Inject
    lateinit var dateTimeFormatter:DateTimeFormatter

    @Inject
    lateinit var threadPool:ExecutorService

    private class WikiSearchHandler(service:WikiSearchMessengerService):Handler(Looper.myLooper()!!)
    {
        private val mWeakReference = WeakReference(service)

        private fun wikiSearchCallback(text:String, maxRows:Int)
        {
            val service = mWeakReference.get()!!
            val wikiService = service.wikiSearchService
            val call = wikiService.findByQ(text, maxRows, "csystem")

            call.enqueue(object : retrofit2.Callback<WikiSearch>
            {
                override fun onResponse(call: Call<WikiSearch>, response: Response<WikiSearch>)
                {
                    val wikiSearch = response.body()

                    if (wikiSearch?.wikiInfo != null){
                        wikiSearch.wikiInfo.forEach { Toast.makeText(service, it.summary, Toast.LENGTH_SHORT).show() }
                    }else
                        Toast.makeText(service, "Error in geonames service", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<WikiSearch>, ex: Throwable)
                {
                    Toast.makeText(service, "Exception occurred:${ex.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

        override fun handleMessage(msg: Message)
        {
            when(msg.what){
//                WHAT_WIKI_SEARCH -> wikiSearchCallback(msg.obj.toString(), msg.arg1)
                Common.WHAT_WIKI_SEARCH -> wikiSearchCallback(msg.data.getString(Common.BUNDLE_KEY_TEXT)!!, msg.data.getInt(Common.BUNDLE_KEY_MAX_ROWS))
            }
            super.handleMessage(msg)
        }
    }

    override fun onBind(intent: Intent): IBinder
    {
        Toast.makeText(this, "client bounded", Toast.LENGTH_SHORT).show()
        mHandler = WikiSearchHandler(this)
        mMessenger = Messenger(mHandler)

        return mMessenger.binder
    }
}