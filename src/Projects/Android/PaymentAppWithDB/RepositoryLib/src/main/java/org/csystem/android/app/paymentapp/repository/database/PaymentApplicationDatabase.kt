package org.csystem.android.app.paymentapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.csystem.android.app.paymentapp.repository.converter.LocalDateConverter
import org.csystem.android.app.paymentapp.repository.converter.LocalDateTimeConverter
import org.csystem.android.app.paymentapp.repository.dao.ILoginInfoDao
import org.csystem.android.app.paymentapp.repository.dao.IPaymentDao
import org.csystem.android.app.paymentapp.repository.dao.IUserDao
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.User

@Database(entities = [User::class, Payment::class, LoginInfo::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class, LocalDateConverter::class)
abstract class PaymentApplicationDatabase:RoomDatabase()
{
    abstract fun createUserDao():IUserDao
    abstract fun createPaymentDao():IPaymentDao
    abstract fun createLoginInfoDao():ILoginInfoDao
}