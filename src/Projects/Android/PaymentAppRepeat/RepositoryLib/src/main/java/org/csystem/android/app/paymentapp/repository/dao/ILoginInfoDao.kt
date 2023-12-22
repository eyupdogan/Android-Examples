package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.join.UserToLogins

@Dao
interface ILoginInfoDao {
    @Query("""SELECT * FROM users u INNER JOIN login_info li on u.username = li.username 
        WHERE u.username = :username
    """)
    fun findByUserName(username: String) : List<LoginInfo>

    @Query("""SELECT li.id, 
        li.username, 
        li.password, 
        li.success, 
        li.login_date_time 
        FROM users u INNER JOIN login_info li on u.username = li.username 
        WHERE u.username = :username AND li.success = 1
    """)
    fun findSuccessByUserName(username: String) : List<LoginInfo>

    @Query("""SELECT li.id, 
        li.username, 
        li.password, 
        li.success, 
        li.login_date_time 
        FROM users u INNER JOIN login_info li on u.username = li.username 
        WHERE u.username = :username AND li.success = 0
    """)
    fun findFailsByUserName(username: String) : List<LoginInfo>

    @Query("""SELECT li.id, 
        li.username, 
        li.password, 
        li.success, 
        li.login_date_time 
        FROM users u INNER JOIN login_info li on u.username = li.username 
        WHERE u.username = :username AND li.success = 0
    """)

    fun findLastSuccessByUserName(username: String) : List<LoginInfo>

    @Query("""SELECT li.id, 
        li.username, 
        li.password, 
        li.success, 
        li.login_date_time 
        FROM users u INNER JOIN login_info li on u.username = li.username 
        WHERE u.username = :username AND li.success = 0
    """)
    fun findLastFailByUserName(username: String) : List<LoginInfo>

    @Insert
    fun save(loginInfo: LoginInfo)
}