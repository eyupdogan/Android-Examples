package org.csystem.android.app.repository.api.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://fakestoreapi.com"

@Module
@InstallIn(SingletonComponent::class)
object FakeStoreRetrofitModule
{
    @Provides
    @Singleton
    fun create(): Retrofit
    {
        val builder = OkHttpClient.Builder()
        val gsonParser = GsonBuilder().setLenient()

        return Retrofit.Builder().run {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gsonParser.create()))
            client(builder.build())
            build()
        }
    }
}