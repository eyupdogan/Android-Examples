package org.csystem.android.app.paymentapp.global.util

import android.content.Intent
import android.os.Build
import org.csystem.android.app.paymentapp.data.service.dto.LoginInfoDTO
import org.csystem.android.app.paymentapp.global.keys.LOGIN_INFO

fun getLoginInfo(intent: Intent):LoginInfoDTO
{
    return when {
        Build.VERSION.SDK_INT < 33 -> intent.getSerializableExtra(LOGIN_INFO) as LoginInfoDTO
        else -> intent.getSerializableExtra(LOGIN_INFO, LoginInfoDTO::class.java) as LoginInfoDTO
    }
}