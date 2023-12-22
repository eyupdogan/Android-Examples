package org.csystem.app.multipleactivity.library.databinding.converter

import androidx.databinding.InverseMethod

// PaymentQuantityStringConverter
object PaymentQuantityStringConverter
{
    private var mFail:Boolean = false

    val fail:Boolean
        get() = mFail

    var failStr = ""
    @InverseMethod("toStr")
    fun toInt(str:String):Int
    {
        return try {
            mFail = false
            str.toInt()
        }catch (ignore:NumberFormatException){
            mFail = true
            0
        }
    }
    fun toStr(quantity:Int):String = quantity.toString()
}