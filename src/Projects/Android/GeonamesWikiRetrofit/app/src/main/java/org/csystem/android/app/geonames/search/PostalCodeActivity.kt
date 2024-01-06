package org.csystem.android.app.geonames.search

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonames.search.databinding.ActivityPostalCodeBinding
import org.csystem.android.app.geonames.search.global.key.POSTAL_CODE_INFO_DTO
import org.csystem.android.app.geonames.search.global.key.QUERY_LIST
import org.csystem.android.app.geonames.search.global.key.SETTINGS_INPUT
import org.csystem.android.app.geonames.search.global.key.USERNAME
import org.csystem.android.app.geonames.search.postalcode.api.IPostalCodeService
import org.csystem.android.app.geonames.search.postalcode.api.entity.PostalCode
import org.csystem.android.app.geonames.search.postalcode.api.entity.PostalCodeSearch
import org.csystem.android.app.geonames.search.repositorylib.dal.GeonamesHelper
import org.csystem.android.app.geonames.search.repositorylib.dto.PostalCodeListDBDTO
import org.csystem.android.app.geonames.search.repositorylib.dto.QuerySearchDTO
import org.csystem.android.app.geonames.search.viewmodel.listeners.PostalCodeActivityListenerViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class PostalCodeActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityPostalCodeBinding
    private lateinit var mUsername:String
    private lateinit var mCountryCode:String
    private var mPostalCodeList:List<PostalCode>? = null

    @Inject
    lateinit var geonamesHelper: GeonamesHelper

    @Inject
    lateinit var mPostalCodeService:IPostalCodeService


    private fun initBoundBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_postal_code)
        mBinding.viewModel = PostalCodeActivityListenerViewModel(this)
        mBinding.postalCodeArrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<PostalCode>())
        mBinding.postalCodeSearch = PostalCodeListDBDTO()

        loadData()
    }

    private fun loadData()
    {
        with(getSharedPreferences(SETTINGS_INPUT, MODE_PRIVATE)){
            mUsername = getString(USERNAME, "")!!
            mCountryCode = "tr"
        }
    }

    private fun initialize()
    {
        initBoundBinding()
    }

    fun getButtonClicked()
    {
        mBinding.postalCodeArrayAdapter!!.clear()
        if (geonamesHelper.existPostalCodeByPostalCodeListDTO(mBinding.postalCodeSearch!!)){

            geonamesHelper.saveQuerySearch(QuerySearchDTO(mBinding.postalCodeSearch!!.postalCode, "postal_code", mBinding.postalCodeSearch!!.rowCount))

            mPostalCodeList = geonamesHelper.findPostalCodeByPostalCodeListDTO(mBinding.postalCodeSearch!!)
            geonamesHelper.findPostalCodeByPostalCodeListDTO(mBinding.postalCodeSearch!!).forEach {
                mBinding.postalCodeArrayAdapter!!.add(it)
                mBinding.postalCodeArrayAdapter!!.notifyDataSetChanged() }
        }else {
            val call = mPostalCodeService.findByQ(mBinding.postalCodeSearch!!.postalCode.toInt(),
                mBinding.postalCodeSearch!!.rowCount, mUsername, mCountryCode)

            call.enqueue(object :Callback<PostalCodeSearch>{
                override fun onResponse(
                    call: Call<PostalCodeSearch>,
                    response: Response<PostalCodeSearch>) {
                    val result = response.body()

                    if (result?.postalCodes != null){
                        mPostalCodeList = result.postalCodes
                        result.postalCodes.forEach {
                            mBinding.postalCodeArrayAdapter!!.add(it)
                            mBinding.postalCodeArrayAdapter!!.notifyDataSetChanged()
                        }
                    }else
                        Toast.makeText(this@PostalCodeActivity, "Error in service",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<PostalCodeSearch>, ex: Throwable)
                {
                    Toast.makeText(this@PostalCodeActivity, "Error occurred ${ex.message}",Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    fun closeButtonClicked()
    {
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun postalCodeItemClicked(pos:Int)
    {
        val selectedPostalCode = mBinding.postalCodeArrayAdapter!!.getItem(pos)!!

        Intent(this, PostalCodeInfoActivity::class.java).apply {
            putExtra(POSTAL_CODE_INFO_DTO, geonamesHelper.toPostalCodeDTO(selectedPostalCode))
            startActivity(this)
        }
    }

    private fun saveCallback()
    {
        geonamesHelper.savePostalCodeList(mBinding.postalCodeSearch!!, mPostalCodeList!!)


        with(mPostalCodeList) {
            if (this != null) {
                geonamesHelper.savePostalCodeList(mBinding.postalCodeSearch!!, this)
            } else
                Toast.makeText(this@PostalCodeActivity, "Error in save", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.save_postal_code_alert_dialog_title)
            .setMessage(R.string.save_postal_code_alert_dialog_message)
            .setPositiveButton(R.string.save_postal_code_alert_dialog_ok_text) {_,_ -> saveCallback()}
            .setNegativeButton(R.string.save_postal_code_alert_dialog_no_text) {_,_ -> }
            .create().show()
    }

    fun queryListButtonClicked()
    {
        Intent(this, QueryListActivity::class.java).apply {
            putExtra(QUERY_LIST, "postal_code")
            startActivity(this)
        }
    }
}