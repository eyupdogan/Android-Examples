package org.csystem.android.app.repository.dal

import android.content.Context
import android.widget.Toast
import org.csystem.android.app.repository.api.dao.IProductService
import org.csystem.android.app.repository.api.entity.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FakeStoreHelper @Inject constructor()
{
    @Inject
    lateinit var productListService:IProductService

    private var productList:List<Product>? = null

    fun getAllProducts(context: Context):List<Product>?
    {
        val call = productListService.getProducts()
        call.enqueue(object :Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>)
            {
                val products = response.body()
                if (products!!.isNotEmpty()){
                    productList = products
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable)
            {
                Toast.makeText(context, "Error occurred onFailure", Toast.LENGTH_SHORT).show()
            }
        })

        return productList
    }
}