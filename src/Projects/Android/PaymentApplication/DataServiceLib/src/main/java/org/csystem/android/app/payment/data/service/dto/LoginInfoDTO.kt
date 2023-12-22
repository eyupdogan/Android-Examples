package org.csystem.android.app.payment.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoDTO(
    var username: String = "",
    var password: String = "",
    var success: Boolean = true,
    var loginDateTimeStr: String = ""
):Serializable