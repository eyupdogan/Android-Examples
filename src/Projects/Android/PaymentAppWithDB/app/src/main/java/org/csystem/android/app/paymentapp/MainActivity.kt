/*--------------------------------------------------------------------------------------------------
    NOT: mapStruct default constructor bekler. Örneğin yukarıdaki örnekte User class ını sadeleştirdik
    (UserSaveDTO). Ancak eğer User sınıfında default constructor olmazsa hata alıyoruz. Biz elemanlara
    default argumanlar verince hata ortadan kalktı
--------------------------------------------------------------------------------------------------*/

package org.csystem.android.app.paymentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.paymentapp.databinding.ActivityMainBinding
import org.csystem.android.app.paymentapp.viewmodel.MainActivityListenerViewModel

class MainActivity : AppCompatActivity()
{
    private lateinit var mBinding: ActivityMainBinding

    private fun initialize()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun registerButtonClicked()
    {
        Intent(this, RegisterActivity::class.java).apply {
            startActivity(this)
        }
    }

    fun loginButtonClicked()
    {
        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }
    }
}