package org.csystem.android.app.paymentapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.paymentapp.data.service.PaymentApplicationDataService
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoStatusDTO
import org.csystem.android.app.paymentapp.databinding.ActivityLoginInformationBinding
import org.csystem.android.app.paymentapp.global.util.getLoginInfo
import org.csystem.android.app.paymentapp.viewmodel.LoginInformationActivityListenerViewModel
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class LoginInformationActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityLoginInformationBinding
    private lateinit var mLoginInfo: LoginInfoDTO

    @Inject
    lateinit var dataService: PaymentApplicationDataService

    @Inject
    lateinit var threadPool:ScheduledExecutorService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_information)
        mBinding.viewModel = LoginInformationActivityListenerViewModel(this)
        mBinding.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ArrayList<LoginInfoStatusDTO>())
    }

    private fun initLoginInfo()
    {
        mLoginInfo = getLoginInfo(intent)
    }

    private fun initialize()
    {
        initBinding()
        initLoginInfo()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun successLoginsButtonClickedCallback()
    {
        try {
            runOnUiThread {
                with(mBinding.adapter!!){
                clear()
                dataService.findSuccessLoginInfoByUsername(mLoginInfo.username).forEach { add(it) }
                }
            }
        }catch (ex:DataServiceException){
            runOnUiThread { Toast.makeText(this, "Data problem ${ex.message}", Toast.LENGTH_SHORT).show() }
        }catch (ex:Throwable){
            runOnUiThread { Toast.makeText(this, "Problem occurred Try again later ${ex.message}", Toast.LENGTH_SHORT).show() }
        }
    }

    fun successLoginsButtonClicked() = threadPool.execute { successLoginsButtonClickedCallback() }

    private fun failLoginsButtonClickedCallback()
    {
        try {
            with(mBinding.adapter!!){
                runOnUiThread {clear()}
                dataService.findFailLoginInfoByUsername(mLoginInfo.username).let {failLogins ->
                    if(failLogins.isNotEmpty())
                        runOnUiThread { failLogins.forEach { add(it) } }
                    else
                        runOnUiThread { Toast.makeText(this@LoginInformationActivity, "No fail logins", Toast.LENGTH_SHORT).show() }
                }
            }

        }catch (ex:DataServiceException){
            runOnUiThread {
                Toast.makeText(this, "Data problem ${ex.message}", Toast.LENGTH_SHORT).show()
            }
        }catch (ex:Throwable){
            runOnUiThread {
                Toast.makeText(this, "Problem occurred Try again later ${ex.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun failLoginsButtonClicked()= threadPool.execute { failLoginsButtonClickedCallback() }

    fun loginInformationItemClicked(pos:Int)
    {
        mBinding.adapter!!.getItem(pos).toString().run{
            AlertDialog.Builder(this@LoginInformationActivity).setTitle(R.string.alertdialog_login_info_title_text)
                .setMessage(this)
                .setPositiveButton(R.string.alertdialog_login_info_ok_text) {_, _->}
                .create().show()
        }
    }

    fun closeButtonClicked()
    {
        finish()
    }
}