package org.csystem.android.app.hiltapprepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltapprepeat.datetime.DateTimeInfo
import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.DateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{

    @Inject
    lateinit var dateTimeInfo: DateTimeInfo

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this, dateTimeInfo.toString(), Toast.LENGTH_SHORT).show()
    }
}