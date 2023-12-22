package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.csystem.android.app.paymentapp.repository.entity.join.UserToPayments
import org.csystem.android.app.paymentapp.repository.entity.Payment

@Dao
interface IPaymentDao
{
    @Query("SELECT * FROM users WHERE username = :username")
    @Transaction
    fun findByUsername(username:String):List<UserToPayments>

    @Insert
    fun save(payment: Payment)
}