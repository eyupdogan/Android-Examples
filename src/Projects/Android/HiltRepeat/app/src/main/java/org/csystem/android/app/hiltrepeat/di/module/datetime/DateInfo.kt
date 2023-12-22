// DateInfo
package org.csystem.android.app.hiltrepeat.di.module.datetime

import org.csystem.android.app.hiltrepeat.di.module.formatter.annotation.DateFormatterInterceptor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateInfo @Inject constructor(@DateFormatterInterceptor var formatter: DateTimeFormatter){
    @Inject
    lateinit var date:LocalDate

    override fun toString(): String = formatter.format(date)
}
