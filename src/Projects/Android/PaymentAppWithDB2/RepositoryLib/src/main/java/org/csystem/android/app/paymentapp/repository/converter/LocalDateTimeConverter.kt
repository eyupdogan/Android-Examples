package org.csystem.android.app.paymentapp.repository.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter
{
    @TypeConverter
    fun toLocalDateTime(milliSeconds:Long):LocalDateTime
    {
        return Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    @TypeConverter
    fun toStr(localDateTime: LocalDateTime):Long
    {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}