package org.csystem.android.app.wikisearchretrofit.viewmodel.converter

import androidx.databinding.InverseMethod
import java.lang.NumberFormatException

object MaxRowStringConverter
{
    @InverseMethod("toStr")
    fun toMaxRow(str: String): Int = try {
        str.toInt()
    } catch (_: NumberFormatException) {
        0
    }

    fun toStr(maxRow: Int): String = maxRow.toString()
}