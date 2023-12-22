package org.csystem.android.app.multipleactivity.viewmodel

import java.time.LocalDate
import java.time.Month
import java.time.temporal.ChronoUnit

data class RegisterInfo(var firstname:String = "", var lastName:String = "",
                        var email:String = "", var password:String = "",
                        var birthDate:LocalDate = LocalDate.of(1993, Month.OCTOBER, 24),
                        var middleName:String? = "")
{
    val age:Double
        get() = ChronoUnit.DAYS.between(birthDate, LocalDate.now()) / 365.0

    val fullName:String
        get()
        {
            var middleStr = middleName.run {
                this ?: ""
            }
            middleStr += if (middleStr.isNotEmpty()) " " else ""

            return "${"$firstname "}$middleStr$lastName"
        }
}