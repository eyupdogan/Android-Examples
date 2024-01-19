package org.csystem.android.app.payment.repository.dao

import com.karandev.util.data.repository.ICrudRepository
import org.csystem.android.app.payment.repository.entity.Payment

interface IPaymentRepository: ICrudRepository<Payment, Long>
{
    fun findByUsername(username:String):List<Payment>
}