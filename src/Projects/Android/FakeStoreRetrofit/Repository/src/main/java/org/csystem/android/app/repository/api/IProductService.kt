package org.csystem.android.app.repository.api

import retrofit2.Call
import retrofit2.http.GET

interface IProductService
{
    @GET("/products")
    fun getAllProducts(): Call<ProductList>
}