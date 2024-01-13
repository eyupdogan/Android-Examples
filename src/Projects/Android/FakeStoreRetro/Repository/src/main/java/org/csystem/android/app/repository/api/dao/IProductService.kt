package org.csystem.android.app.repository.api.dao

import org.csystem.android.app.repository.api.entity.Product
import retrofit2.Call
import retrofit2.http.GET

interface IProductService
{
    @GET("/products")
    fun getProducts():Call<List<Product>>
}