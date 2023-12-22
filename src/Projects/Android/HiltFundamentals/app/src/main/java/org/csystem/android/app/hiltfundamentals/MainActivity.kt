package org.csystem.android.app.hiltfundamentals

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltfundamentals.datetime.DateInfo
import org.csystem.android.app.hiltfundamentals.datetime.DateTimeInfo
import org.csystem.android.app.hiltfundamentals.datetime.TimeInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTimeInfo:DateTimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo:TimeInfo


    private fun showDateTime()
    {
        StringBuilder().apply {
            append("DateTime: ")
            append(dateTimeInfo.toString())
            append("Date: ")
            append(dateInfo.toString())
            append("Time: ")
            append(timeInfo.toString())
        }.also {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDateTime()
    }
}