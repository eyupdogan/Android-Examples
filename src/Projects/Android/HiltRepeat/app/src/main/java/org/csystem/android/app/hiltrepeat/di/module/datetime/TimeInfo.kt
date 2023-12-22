// TimeInfo
package org.csystem.android.app.hiltrepeat.di.module.datetime

import org.csystem.android.app.hiltrepeat.di.module.formatter.annotation.TimeFormatterInterceptor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeInfo @Inject constructor() {
    @Inject
    lateinit var time:LocalTime

    @Inject
    @TimeFormatterInterceptor
    lateinit var formatter:DateTimeFormatter

    override fun toString(): String = formatter.format(time)
}
