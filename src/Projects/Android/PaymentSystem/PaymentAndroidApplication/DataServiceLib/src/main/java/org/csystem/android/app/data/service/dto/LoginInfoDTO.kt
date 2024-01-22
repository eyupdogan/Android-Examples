package org.csystem.android.app.data.service.dto

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class LoginInfoDTO(
    var username: String = "",
    var password: String = ""
) : Serializable
{
}