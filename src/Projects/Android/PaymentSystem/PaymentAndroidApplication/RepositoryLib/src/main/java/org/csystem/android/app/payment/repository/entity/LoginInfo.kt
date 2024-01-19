package org.csystem.android.app.payment.repository.entity

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfo(
    var id: Long,
    var username: String,
    var success: Boolean,
    var loginDateTime: LocalDateTime
) : Serializable