package org.csystem.android.app.paymentapp.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoStatusDTO(var username: String = "", var password: String = "",
    var success: Boolean = true, var loginDateTimeStr: String) : Serializable
{
    override fun toString() = "Login Date Time:$loginDateTimeStr, Status:${if (success) "Success" else "Fail"}"
}