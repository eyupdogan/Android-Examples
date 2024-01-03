package org.csystem.android.app.wikisearchrepeat.viewmodel.converter

import androidx.databinding.InverseMethod

object MaxRowStringConverter
{
    @InverseMethod("toStr")
    fun toMaxRow(str:String):Int = try {
        str.toInt()
    }catch (ex:NumberFormatException){
        0
    }

    fun toStr(maxRow:Int):String = maxRow.toString()
}