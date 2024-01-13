package org.csystem.android.app.fakestoreretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.fakestoreretro.databinding.ActivityMainBinding
import org.csystem.android.app.fakestoreretro.di.module.IProductMapper
import org.csystem.android.app.fakestoreretro.viewmodel.MainActivityListenerViewModel
import org.csystem.android.app.fakestoreretro.viewmodel.data.ProductData
import org.csystem.android.app.repository.api.dao.IProductService
import org.csystem.android.app.repository.api.entity.Product
import org.csystem.android.app.repository.dal.FakeStoreHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var productMapper: IProductMapper

    @Inject
    lateinit var productListService: IProductService

    @Inject
    lateinit var fakeStoreHelper: FakeStoreHelper


    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.productListAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<ProductData>())
        mBinding.viewModel = MainActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun getProductsButtonClicked()
    {
        mBinding.productListAdapter!!.clear()

        val call = productListService.getProducts()

        call.enqueue(object: Callback<List<Product>>
        {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>)
            {
                val productList = response.body()
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