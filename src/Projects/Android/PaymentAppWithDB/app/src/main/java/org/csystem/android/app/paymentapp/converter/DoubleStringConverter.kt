package org.csystem.android.app.paymentapp.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DoubleStringConverter
{
    @InverseMethod("toStr")
    fun toDouble(str:String):Double
    {
        var result = 0.0
        try {
            result = str.toDouble()
            if(result < 0)
                throw NumberFormatException()
        }catch (_:NumberFormatException){

        }
        return result
    }

    fun toStr(value: Double):String = value.toString()

}