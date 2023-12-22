package org.csystem.android.app.randomgenerator.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object MaximumStringConverter
{
    @InverseMethod("toStr")
    fun toMaximum(str:String):Int
    {
        var result = 0
        try {
            result = str.toUInt().toInt() // işaretsiz bir biçimde alıyor eksi koyulamaz başına
        }catch (_:NumberFormatException){

        }
        return result
    }

    fun toStr(value:Int):String = value.toString()
}