package org.csystem.android.app.hiltapprepeat.converter

import androidx.databinding.InverseMethod

object NumberToStringConverter
{
    @InverseMethod("toStr")
    fun toInt(str:String):Int = try {
        str.toInt()
    }   catch (ex:NumberFormatException){
        0
    }

    fun toStr(value:Int):String = value.toString()
}