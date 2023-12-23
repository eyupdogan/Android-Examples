package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.paymentapp.repository.entity.User

@Dao
interface IUserDao
{
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun findByUserNameAndPassword(username:String, password:String):User?

    fun existsByUserNameAndPassword(username:String, password:String) =
        findByUserNameAndPassword(username, password) != null

    @Insert
    fun save(user: User)

    @Query("SELECT * FROM users WHERE username = :username")
    fun existsById(username:String?):Boolean
}