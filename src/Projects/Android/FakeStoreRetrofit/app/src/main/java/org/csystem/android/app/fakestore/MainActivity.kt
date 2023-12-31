package org.csystem.android.app.fakestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.fakestore.databinding.ActivityMainBinding
import org.csystem.android.app.fakestore.di.module.mapper.IProductMapper
import org.csystem.android.app.fakestore.viewmodel.data.ProductData
import org.csystem.android.app.fakestore.viewmodel.listeners.MainActivityListenerViewModel
import org.csystem.android.app.repository.api.IProductService
import org.csystem.android.app.repository.api.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var productListService: IProductService

    @Inject
    lateinit var productMapper: IProductMapper

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun initData()
    {
        mBinding.viewModel = MainActivityListenerViewModel(this)
        mBinding.productListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<ProductData>())
    }

    private fun initialize()
    {
        initBinding()
        initData()
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun getAllProductsButtonClicked()
    {
        mBinding.productListAdapter!!.clear()
        val call = productListService.getAllProducts()

        call.enqueue(object:Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>)
            {
                val productList = response.body()
                Log.d("exvep", "productList?.list.toString()")
                if(productList!!.isNotEmpty()){
                    productList.forEach {
                        mBinding.productListAdapter!!.add(productMapper.toProductDataDTO(it))
                        mBinding.productListAdapter!!.notifyDataSetChanged()
                    }
                }else
                    Toast.makeText(this@MainActivity, "Error occurred", Toast.LENGTH_SHORT).show()
            }



            override fun onFailure(call: Call<List<Product>>, t: Throwable)
            {
                Toast.makeText(this@MainActivity, "Error occurred onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}