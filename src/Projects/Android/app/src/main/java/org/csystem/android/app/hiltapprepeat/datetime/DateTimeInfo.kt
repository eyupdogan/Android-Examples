package org.csystem.android.app.hiltapprepeat.datetime

import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.DateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateTimeInfo @Inject constructor()
{
    @Inject
    lateinit var dateTime:LocalDateTime

    @Inject
    @DateTimeFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun toString(): String = formatter.format(dateTime)
}