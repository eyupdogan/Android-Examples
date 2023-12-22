package org.csystem.android.app.hiltrepeat.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object NumberToStringConverter
{
    @InverseMethod("toStr")
    fun toInt(str:String):Int
    {
        return try
        {
            str.toInt()
        }catch (ex:NumberFormatException){
            0
        }
    }


    fun toStr(value:Int):String = value.toString()

}