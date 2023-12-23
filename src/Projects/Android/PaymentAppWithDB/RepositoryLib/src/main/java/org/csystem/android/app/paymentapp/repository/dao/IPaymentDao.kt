package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.join.UserPaymentInfo

@Dao
interface IPaymentDao
{
    @Query("""SELECT p.username, p.description, p.quantity, p.unit_price
         FROM users u INNER JOIN payments p ON u.username = p.username
        WHERE u.username = :username
    """)
    fun findByUsername(username:String):List<UserPaymentInfo>

    @Insert
    fun save(payment: Payment)
}