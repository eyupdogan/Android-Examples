package org.csystem.android.app.generator.random.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object BoundStringConverter
{
    @InverseMethod("toStr")
    fun toInt(str:String):Int = try {
        str.toInt()
    }catch (ex:NumberFormatException){
        0
    }

    fun toStr(count:Int):String = count.toString()
}