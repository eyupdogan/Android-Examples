package org.csystem.android.app.hiltapprepeat

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltapprepeat.di.module.datetime.DateInfo
import org.csystem.android.app.hiltapprepeat.di.module.datetime.DateTimeInfo
import org.csystem.android.app.hiltapprepeat.di.module.datetime.TimeInfo
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    @Inject
    lateinit var dateInfo: DateInfo

    @Inject
    lateinit var timeInfo: TimeInfo

    private fun showDateTime()
    {
        StringBuilder().apply {
            append("DateTime:").append(dateTimeInfo.toString())
            append("Date:").append(dateInfo.toString())
            append("Time:").append(timeInfo.toString())
        }.also {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showDateTime()
    }

    fun calculateButtonClicked()
    {
        Intent(this, CalculateActivity::class.java).apply {
            startActivity(this)
        }
        Toast.makeText(this, "fsşdfşsd",Toast.LENGTH_SHORT).show()
    }
}