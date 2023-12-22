package org.csystem.android.app.hiltapplication.binding.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object OperationConverter
{
    @InverseMethod("toStr")
    fun toChar(str:String):Char
    {
        return if(str.length != 1) '+' else str[0]
    }
    fun toStr(op:Char):String = op.toString()
}