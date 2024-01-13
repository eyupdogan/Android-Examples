package org.csystem.android.app.repository.api.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.csystem.android.app.repository.global.FAKE_STORE_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object FakeStoreRetrofitModule
{
    @Provides
    fun createFakeStoreRetrofit():Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(FAKE_STORE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(OkHttpClient.Builder().build())
            .build()
    }
}