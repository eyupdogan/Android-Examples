// IUserDao
package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.paymentapp.repository.entity.User

@Dao
interface IUserDao
{
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun findByUsernameAndPassword(username:String, password:String):User?

    fun existsByUsernameAndPassword(username:String, password:String) =
        findByUsernameAndPassword(username, password) != null

    @Insert
    fun save(user: User)

    @Query("SELECT EXISTS(SELECT * FROM users WHERE username = :username)")
    fun existsById(username: String?): Boolean
}