package org.csystem.android.app.search.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IGeonamesWikiSearchService
{
    @GET("/wikipediaSearchJSON")
    fun findByQ(
        @Query("q") text: String,
        @Query("maxRows") rowCount: Int,
        @Query("username") username: String
    ): Call<WikiSearch>
}