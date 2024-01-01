package org.csystem.android.app.paymentapp.repository.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateConverter
{
    @TypeConverter
    fun toLocalDate(milliSeconds:Long):LocalDate
    {
        return Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.systemDefault()).toLocalDate()
    }

    @TypeConverter
    fun toStr(localDate: LocalDate):Long
    {
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}