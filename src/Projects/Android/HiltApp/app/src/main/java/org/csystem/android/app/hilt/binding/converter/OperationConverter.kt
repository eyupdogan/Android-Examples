package org.csystem.android.app.hilt.binding.converter

import androidx.databinding.InverseMethod

object OperationConverter {

    @InverseMethod("toStr")
    @JvmStatic
    fun toChar(str:String):Char = if(str.length != 1) '+' else str[0]
    @JvmStatic
    fun toStr(op:Char):String = op.toString()
}