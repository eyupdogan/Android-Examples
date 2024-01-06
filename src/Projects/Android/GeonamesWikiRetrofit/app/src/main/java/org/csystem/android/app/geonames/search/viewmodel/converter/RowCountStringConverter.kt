package org.csystem.android.app.geonames.search.viewmodel.converter

import androidx.databinding.InverseMethod

object RowCountStringConverter
{
    @InverseMethod("toStr")
    fun toInt(str:String):Int = try {
        str.toInt()
    }catch (ex:NumberFormatException){
        0
    }

    fun toStr(rowCount:Int):String = rowCount.toString()
}