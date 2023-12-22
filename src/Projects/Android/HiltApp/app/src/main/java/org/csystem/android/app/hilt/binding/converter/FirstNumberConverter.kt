package org.csystem.android.app.hilt.binding.converter

import androidx.databinding.InverseMethod

object FirstNumberConverter {

    @InverseMethod("toStr")
    @JvmStatic
    fun toInt(str:String):Int =
         try {
            str.toInt()
        }catch (ignore:NumberFormatException){
            0
        }

    @JvmStatic
    fun toStr(value:Int):String = value.toString()
}