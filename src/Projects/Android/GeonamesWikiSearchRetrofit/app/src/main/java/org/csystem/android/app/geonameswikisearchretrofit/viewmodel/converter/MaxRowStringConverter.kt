package org.csystem.android.app.geonameswikisearchretrofit.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object MaxRowStringConverter
{
    @InverseMethod("toStr")
    fun toMaxRow(str:String):Int
    {
        var result = 0
        try {
            result = str.toInt()
        }catch (ex:NumberFormatException){

        }
        return result
    }

    fun toStr(maxRow:Int):String
    {
        return maxRow.toString()
    }
}