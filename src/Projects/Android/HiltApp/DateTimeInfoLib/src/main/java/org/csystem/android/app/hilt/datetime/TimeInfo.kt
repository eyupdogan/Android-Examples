package org.csystem.android.app.hilt.datetime

import dagger.hilt.android.scopes.ActivityScoped
import org.csystem.android.util.datetime.di.module.annotation.SystemLocalTimeInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalTimeFormatterInterceptor
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@ActivityScoped // default
class TimeInfo @Inject constructor(@LocalTimeFormatterInterceptor var formatter: DateTimeFormatter,
                                   @SystemLocalTimeInterceptor var time:LocalTime){


    override fun toString():String = formatter.format(time)
}