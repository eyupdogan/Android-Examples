package org.csystem.android.app.hiltfundamentals.datetime

import org.csystem.android.app.hiltfundamentals.di.annotation.DateFormatterInterceptor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateInfo @Inject constructor(@DateFormatterInterceptor var formatter:DateTimeFormatter){
    @Inject
    lateinit var date:LocalDate

        override fun toString() = formatter.format(date)
}


