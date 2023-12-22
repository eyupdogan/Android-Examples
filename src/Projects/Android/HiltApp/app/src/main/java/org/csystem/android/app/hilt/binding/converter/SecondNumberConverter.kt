package org.csystem.android.app.hilt.binding.converter

import androidx.databinding.InverseMethod

object SecondNumberConverter {

    @InverseMethod("toStr")
    @JvmStatic
    fun toInt(str:String):Int
    {
        var result = 0
        try {
            result = str.toInt()
        }catch (ignore:NumberFormatException){

        }
        return result
    }
    @JvmStatic
    fun toStr(value:Int):String = value.toString()
}