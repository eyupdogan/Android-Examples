package org.csystem.android.app.hiltapplication.datetime

import org.csystem.android.util.datetime.di.module.annotation.SystemLocalDateTimeInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateTimeInfo @Inject constructor()
{
    @Inject
    @SystemLocalDateTimeInterceptor
    lateinit var dateTime:LocalDateTime

    @Inject
    @LocalDateTimeFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun toString(): String
    {
        return formatter.format(dateTime)
    }

}