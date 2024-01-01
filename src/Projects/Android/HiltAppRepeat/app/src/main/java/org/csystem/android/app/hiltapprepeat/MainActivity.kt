package org.csystem.android.app.hiltapprepeat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.DateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    @Inject
    lateinit var dateTime: LocalDateTime

    @Inject
    @DateTimeFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, formatter.format(dateTime), Toast.LENGTH_SHORT).show()
    }
}