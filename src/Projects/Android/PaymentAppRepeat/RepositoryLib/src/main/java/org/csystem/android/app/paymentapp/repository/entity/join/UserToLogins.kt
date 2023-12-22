package org.csystem.android.app.paymentapp.repository.entity.join

import androidx.room.Embedded
import androidx.room.Relation
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.User

data class UserToLogins(@Embedded val user:User,
                        @Relation(parentColumn = "username", entityColumn = "username")
                           val loginInfoList:List<LoginInfo>)
