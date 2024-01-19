package org.csystem.android.app.payment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.data.service.PaymentApplicationDataService
import org.csystem.android.app.data.service.dto.UserSaveDTO
import org.csystem.android.app.data.service.mapper.IUserMapper
import org.csystem.android.app.payment.databinding.ActivityRegisterBinding
import org.csystem.android.app.payment.viewmodel.RegisterActivityListenerViewModel
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity()
{
    private lateinit var mBinding:ActivityRegisterBinding

    @Inject
    lateinit var dataService: PaymentApplicationDataService

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mBinding.viewModel = RegisterActivityListenerViewModel(this)
        mBinding.user = UserSaveDTO()
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

    fun registerButtonClicked()
    {
        try {
            val user = mBinding.user!!
            if(dataService.saveUser(user))
                Toast.makeText(this, "${user.username} successfully registered", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "${user.username} cannot be registered", Toast.LENGTH_SHORT).show()
        }catch (ex:DataServiceException){
            Toast.makeText(this, "Data problem:${ex.message}",Toast.LENGTH_SHORT).show()
        }catch (ex:Throwable){
            Toast.makeText(this, "Problem occurred",Toast.LENGTH_SHORT).show()
        }
    }

    fun closeButtonClicked()
    {
        finish()
    }
}