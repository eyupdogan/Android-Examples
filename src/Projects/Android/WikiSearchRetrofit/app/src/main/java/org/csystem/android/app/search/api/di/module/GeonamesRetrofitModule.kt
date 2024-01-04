package org.csystem.android.app.search.api.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "http://api.geonames.org"

@Module
@InstallIn(SingletonComponent::class)
object GeonamesRetrofitModule
{
    @Provides
    @Singleton
    fun create():Retrofit
    {
        val builder = OkHttpClient.Builder() // önce builder alıyorum
        val gson = GsonBuilder().setLenient() // hangi json parse ı kullanacağımı belirlemek için GsonBuilder ın setLenient metodunu çağırmamız gerekiyor
        // baseUrl i vermemiz gerekiyor
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson.create()))
            .client(builder.build()).build()
    }
}