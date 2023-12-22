package org.csystem.android.app.paymentapp.repository.entity.join

import androidx.room.Embedded
import androidx.room.Relation
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.User

data class UserToPayments(@Embedded val user:User,
                          @Relation(parentColumn = "username", entityColumn = "username")
                          val payments:List<Payment>)
