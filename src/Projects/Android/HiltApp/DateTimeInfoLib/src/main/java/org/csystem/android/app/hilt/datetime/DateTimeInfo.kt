package org.csystem.android.app.hilt.datetime

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import org.csystem.android.util.datetime.di.module.annotation.SystemLocalDateTimeInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateTimeFormatterInterceptor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@ActivityScoped
class DateTimeInfo @Inject constructor(@LocalDateTimeFormatterInterceptor var formatter:DateTimeFormatter,
                                       @SystemLocalDateTimeInterceptor var dateTime:LocalDateTime,
    @ActivityContext context: Context){

    init {
        Toast.makeText(context, "Date time info created -> ${formatter.format(dateTime)}", Toast.LENGTH_SHORT).show()
    }
    override fun toString():String = formatter.format(dateTime)
}