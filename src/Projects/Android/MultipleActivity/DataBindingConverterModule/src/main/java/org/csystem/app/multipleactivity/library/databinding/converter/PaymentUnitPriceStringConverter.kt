package org.csystem.app.multipleactivity.library.databinding.converter

import androidx.databinding.InverseMethod

// PaymentUnitPriceStringConverter
object PaymentUnitPriceStringConverter
{
    private var mFail:Boolean = false

    val fail:Boolean
        get() = mFail
    var failStr:String = ""
    @InverseMethod("toStr")
    fun toDouble(str:String):Double
    {
        return try {
            mFail = false
            str.toDouble()
        }catch (ignore:NumberFormatException){
            mFail = true
            0.0
        }
    }
    fun toStr(quantity:Double):String = quantity.toString()
}