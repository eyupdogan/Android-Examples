package org.csystem.android.app.payment.repository.entity

import java.io.Serializable
import java.time.LocalDate

data class User(
    var username: String,
    var password:String,
    var firstName: String,
    var middleName: String?,
    var lastName: String,
    var birthDate:LocalDate,
    var registerDate:LocalDate
):Serializable
