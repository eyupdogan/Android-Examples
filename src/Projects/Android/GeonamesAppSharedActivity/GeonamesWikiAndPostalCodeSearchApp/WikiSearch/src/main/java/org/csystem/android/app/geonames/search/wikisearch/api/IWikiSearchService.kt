package org.csystem.android.app.geonames.search.wikisearch.api

import org.csystem.android.app.geonames.search.wikisearch.api.entity.WikiSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWikiSearchService
{
    @GET("/wikipediaSearchJSON")
    fun findByQ(
        @Query("q") text:String,
        @Query("maxRows") rowCount:Int,
        @Query("username") username:String
    ):Call<WikiSearch>
}