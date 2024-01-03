package org.csystem.android.app.org_csystem_android_app_retrofit.di.module

import android.util.Log
import android.widget.Toast
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Module
@InstallIn(ActivityComponent::class)
object GetRequestModule
{
    fun <T> request(call: Call<T>)
    {
        call.enqueue(object : Callback<T>
        {
            override fun onResponse(call: Call<T>, response: Response<T>)
            {

            }

            override fun onFailure(call: Call<T>, ex: Throwable)
            {

            }
        })
    }
}