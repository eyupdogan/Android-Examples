package org.csystem.android.app.paymentapp.repository.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateConverter
{
    @TypeConverter
    fun toLocalDate(str: String):LocalDate
    {
        return LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @TypeConverter
    fun toStr(localDate: LocalDate):String
    {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate)
    }
}