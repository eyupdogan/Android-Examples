package org.csystem.app.multipleactivity.library.databinding.converter

import androidx.databinding.InverseMethod
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object RegisterBirthDateStringConverter
{
    private val mFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private var mFail:Boolean = false

    val fail:Boolean
        get() = mFail
    var failStr = ""
    @InverseMethod("toStr")
    fun toLocalDate(str:String):LocalDate
    {
        var result = LocalDate.of(1993, Month.OCTOBER, 24)
        try {
            mFail = false
            result = LocalDate.parse(str, mFormatter)
        }catch (ignore:DateTimeParseException){
            mFail = true
        }
        return result
    }

    fun toStr(birthDate:LocalDate):String = mFormatter.format(birthDate)
}