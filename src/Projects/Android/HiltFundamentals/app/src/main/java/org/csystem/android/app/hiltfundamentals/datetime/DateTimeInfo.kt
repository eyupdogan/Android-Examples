package org.csystem.android.app.hiltfundamentals.datetime

import org.csystem.android.app.hiltfundamentals.di.annotation.DateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateTimeInfo @Inject constructor(@DateTimeFormatterInterceptor var formatter:DateTimeFormatter){
    @Inject
    lateinit var dateTime:LocalDateTime

        override fun toString() = formatter.format(dateTime)
}


