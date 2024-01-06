package org.csystem.android.app.geonames.search.repositorylib.database.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter
{
    @TypeConverter
    fun toMilliSeconds(localDateTime: LocalDateTime):Long
    {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }

    @TypeConverter
    fun toLocalDateTime(milliSeconds:Long):LocalDateTime
    {
        return Instant.ofEpochMilli(milliSeconds).atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

}