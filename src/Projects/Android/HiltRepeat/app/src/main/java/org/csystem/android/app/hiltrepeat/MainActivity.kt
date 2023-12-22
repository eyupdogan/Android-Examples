/*--------------------------------------------------------------------------------------------------
    IBinaryOperator interface ini implemente ederken IntAddOperation mu yoksa
    IntMultiplyOperation nun mu enjekte edilecek, buna nasıl karar verilecek sistem nasıl
    anlayacak (BIND). Yani biz IntAddOperation veya IntMultiplyOperation u yaratmayacağız,
    biz bu nesneyi de enjekte etmek istiyoruz
--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.hiltrepeat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltrepeat.databinding.ActivityMainBinding
import org.csystem.android.app.hiltrepeat.di.module.datetime.DateInfo
import org.csystem.android.app.hiltrepeat.di.module.datetime.DateTimeInfo
import org.csystem.android.app.hiltrepeat.di.module.datetime.TimeInfo
import org.csystem.android.app.hiltrepeat.di.module.formatter.annotation.DateTimeFormatterInterceptor
import org.csystem.android.app.hiltrepeat.viewModel.MainActivityListenerViewModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    lateinit var timeInfo: TimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    @DateTimeFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    private lateinit var mBinding:ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityListenerViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    private fun showDateTime()
    {
        with(StringBuilder().apply {
            append("DateTime: ").append(dateTimeInfo.toString()).append("\n")
            append("Date: ").append(dateInfo.toString()).append("\n")
            append("Time: ").append(timeInfo.toString())
        }){
            Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
        showDateTime()
    }

    fun calculateButtonClicked()
    {
        Intent(this, CalculateActivity::class.java).apply {
            startActivity(this)
        }
    }
}