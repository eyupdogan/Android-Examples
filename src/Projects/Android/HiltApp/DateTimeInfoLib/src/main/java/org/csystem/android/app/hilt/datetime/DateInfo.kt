package org.csystem.android.app.hilt.datetime

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import org.csystem.android.util.datetime.di.module.annotation.SystemLocalDateInterceptor
import org.csystem.android.util.datetime.di.module.formatter.annotation.LocalDateFormatterInterceptor
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@ActivityScoped
class DateInfo @Inject constructor(
    @LocalDateFormatterInterceptor var formatter: DateTimeFormatter,
    @SystemLocalDateInterceptor var date: LocalDate,
    @ActivityContext var context: Context
) {



    init {
        Toast.makeText(context, "Date info created -> $this", Toast.LENGTH_SHORT).show()
    }


    override fun toString():String = formatter.format(date)
}