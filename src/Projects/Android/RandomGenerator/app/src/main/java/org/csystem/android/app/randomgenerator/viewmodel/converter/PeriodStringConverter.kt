package org.csystem.android.app.randomgenerator.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object PeriodStringConverter
{
    @InverseMethod("toStr")
    fun toPeriod
                (str:String):Long
    {
        var result = 0L
        try {
            result = str.toULong().toLong()
        }catch (_:NumberFormatException){

        }
        return result
    }

    fun toStr(value:Long):String = value.toString()
}