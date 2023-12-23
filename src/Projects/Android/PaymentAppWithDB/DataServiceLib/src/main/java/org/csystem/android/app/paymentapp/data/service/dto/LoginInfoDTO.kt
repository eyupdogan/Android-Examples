/*--------------------------------------------------------------------------------------------------
    Application a user ı save ederken verilmesi gereken alanları sadeleştirerek vermek için UserSaveDTO
    oluşturduk
--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.paymentapp.data.service.dto

import java.io.Serializable
import java.time.LocalDateTime

data class LoginInfoDTO(var username: String = "", var password:String = "") : Serializable