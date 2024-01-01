package org.csystem.android.app.hiltapprepeat.datetime

import org.csystem.android.app.hiltapprepeat.di.module.formatter.annotation.DateFormatterInterceptor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateInfo
{
    @Inject
    lateinit var date:LocalDate

    @Inject
    @DateFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun toString(): String = formatter.format(date)
}