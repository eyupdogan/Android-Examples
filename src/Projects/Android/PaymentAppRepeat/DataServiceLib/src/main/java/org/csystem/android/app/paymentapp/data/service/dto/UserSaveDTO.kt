package org.csystem.android.app.paymentapp.data.service.dto

import java.io.Serializable
import java.time.LocalDate

data class UserSaveDTO(var username: String = "",
                       var password: String = "",
                       var firstname: String = "",
                       var lastName: String = "",
                       var birthDate: LocalDate = LocalDate.now(),
                       var middleName: String? = null):Serializable