package org.csystem.android.app.hiltapplication.datetime

import org.csystem.android.util.datetime.di.module.annotation.SystemLocalTimeInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalTimeFormatterInterceptor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class TimeInfo @Inject constructor(
    @SystemLocalTimeInterceptor var time: LocalTime,
    @LocalTimeFormatterInterceptor var formatter: DateTimeFormatter)
{
    override fun toString(): String
    {
        return formatter.format(time)
    }
}