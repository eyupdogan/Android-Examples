package org.csystem.android.app.paymentapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.paymentapp.constant.what.WHAT_REGISTER_BIRTHDATE_INVALID
import org.csystem.android.app.paymentapp.constant.what.WHAT_REGISTER_DATA_SERVICE_EXCEPTION
import org.csystem.android.app.paymentapp.constant.what.WHAT_REGISTER_EXCEPTION
import org.csystem.android.app.paymentapp.constant.what.WHAT_REGISTER_SUCCESS
import org.csystem.android.app.paymentapp.constant.what.WHAT_REGISTER_USER_NOT_REGISTERED
import org.csystem.android.app.paymentapp.data.service.PaymentApplicationDataService
import org.csystem.android.app.paymentapp.data.service.dto.UserSaveDTO
import org.csystem.android.app.paymentapp.databinding.ActivityRegisterBinding
import org.csystem.android.app.paymentapp.di.module.thread.ExecutorServiceModule
import org.csystem.android.app.paymentapp.viewmodel.RegisterActivityListenerViewModel
import java.lang.ref.WeakReference
import java.time.LocalDate
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity()
{
    @Inject
    lateinit var dataService: PaymentApplicationDataService

    @Inject
    lateinit var threadPool:ScheduledExecutorService

    private lateinit var mBinding: ActivityRegisterBinding
    private lateinit var mHandler:Handler

    private class RegisterHandler(activity: RegisterActivity):Handler(Looper.myLooper()!!)
    {
        private val mWeakReference = WeakReference(activity)

        private fun handleUserNotRegistered()
        {
            with(mWeakReference.get()!!){
                Toast.makeText(this, "${mBinding.user!!.username} cannot be registered", Toast.LENGTH_SHORT).show()
            }
        }

        private fun handleRegisterSuccess()
        {
            with(mWeakReference.get()!!){
                Toast.makeText(this, "${mBinding.user!!.username} successfully registered", Toast.LENGTH_SHORT).show()
                Intent(this, LoginActivity::class.java).apply {
                    startActivity(this)
                }
                finish()
            }
        }

        override fun handleMessage(msg: Message)
        {
            val activity = mWeakReference.get()!!
            when(msg.what){
                WHAT_REGISTER_BIRTHDATE_INVALID -> Toast.makeText(activity, "Invalid date format", Toast.LENGTH_SHORT).show()
                WHAT_REGISTER_USER_NOT_REGISTERED -> handleUserNotRegistered()
                WHAT_REGISTER_DATA_SERVICE_EXCEPTION -> Toast.makeText(activity, "Data problem ${msg.obj}", Toast.LENGTH_SHORT).show()
                WHAT_REGISTER_EXCEPTION -> Toast.makeText(activity, "Problem occurred. Try again later!...", Toast.LENGTH_SHORT).show()
                WHAT_REGISTER_SUCCESS -> handleRegisterSuccess()
            }
        }
    }

    private fun initialize()
    {
        initBinding()
        mHandler = RegisterHandler(this)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = RegisterActivityListenerViewModel(this)
        mBinding.user = UserSaveDTO()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun registerButtonClickedCallback()
    {
        try {
            val user = mBinding.user!!

            if(user.birthDate == LocalDate.now())
                mHandler.sendEmptyMessage(WHAT_REGISTER_BIRTHDATE_INVALID)

            if(dataService.saveUser(user))
                mHandler.sendEmptyMessage(WHAT_REGISTER_SUCCESS)

            else
                mHandler.sendEmptyMessage(WHAT_REGISTER_USER_NOT_REGISTERED)

        }catch (ex:DataServiceException){
            mHandler.sendMessage(mHandler.obtainMessage(WHAT_REGISTER_DATA_SERVICE_EXCEPTION, ex.message))
        }catch (ex:Throwable){
            mHandler.sendEmptyMessage(WHAT_REGISTER_EXCEPTION)
        }
    }

    fun registerButtonClicked() = threadPool.execute { registerButtonClickedCallback() }

    fun closeButtonClicked()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alertdialog_register_close_title_text)
            .setMessage(R.string.alertdialog_register_close_message_text)
            .setPositiveButton(R.string.alertdialog_register_close_yes_button_text) {_,_ -> finish()}
            .setNegativeButton(R.string.alertdialog_register_close_no_button_text){_,_ ->}
            .create().show()
    }
}