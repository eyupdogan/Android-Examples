package org.csystem.android.app.geonames.search.postalcode.api

import org.csystem.android.app.geonames.search.postalcode.api.entity.PostalCodeSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IPostalCodeService
{
    @GET("/postalCodeSearchJSON")
    fun findByQ(
        @Query("postalcode") postalCode: Int,
        @Query("maxRows") rowCount: Int,
        @Query("username") username: String,
        @Query("country") country: String
    ):Call<PostalCodeSearch>
}