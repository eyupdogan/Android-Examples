package org.csystem.android.app.org_csystem_android_app_retrofit.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{
    @Provides
    @Singleton
    fun create(baseUrl:String):Retrofit
    {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            client(OkHttpClient.Builder().build())
            build()
        }
    }
}