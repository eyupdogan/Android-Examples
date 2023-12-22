package org.csystem.android.app.hiltfundamentals.datetime

import org.csystem.android.app.hiltfundamentals.di.annotation.TimeFormatterInterceptor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeInfo @Inject constructor(@TimeFormatterInterceptor var formatter:DateTimeFormatter){
    @Inject
    lateinit var time:LocalTime

        override fun toString() = formatter.format(time)
}


