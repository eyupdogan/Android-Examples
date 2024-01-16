package org.csystem.android.app.wiki.search.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object MaxRowStringConverter
{
    @InverseMethod("toStr")
    fun toInt(str:String):Int = try {
        str.toInt()
    }catch (ex:NumberFormatException){
        0
    }

    fun toStr(value:Int):String = value.toString()
}