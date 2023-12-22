// DateTimeInfo
package org.csystem.android.app.hiltrepeat.di.module.datetime

import org.csystem.android.app.hiltrepeat.di.module.formatter.annotation.DateFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateTimeInfo @Inject constructor() {
    @Inject
    lateinit var dateTime:LocalDateTime

    @Inject
    @DateFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun toString(): String = formatter.format(dateTime)
}
