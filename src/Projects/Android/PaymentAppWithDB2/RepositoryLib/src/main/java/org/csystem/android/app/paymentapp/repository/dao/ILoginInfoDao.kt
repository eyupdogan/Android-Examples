package org.csystem.android.app.paymentapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.karandev.util.data.repository.ICrudRepository
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo

@Dao
interface ILoginInfoDao
{
    @Query("""SELECT li.id,
        li.username,
        li.password,
        li.success,
        li.login_date_time
        FROM users u INNER JOIN login_info li ON u.username = li.username
        WHERE li.username = :username
    """)
    fun findByUsername(username:String):List<LoginInfo>

    @Query("""SELECT li.id,
        li.username,
        li.password,
        li.success,
        li.login_date_time
        FROM users u INNER JOIN login_info li ON u.username = li.username
        WHERE li.username = :username AND li.success = 1 ORDER BY li.login_date_time DESC
    """)
    fun findSuccessLoginByUsername(username: String):List<LoginInfo>

    @Query("""SELECT li.id,
        li.username,
        li.password,
        li.success,
        li.login_date_time
        FROM users u INNER JOIN login_info li ON u.username = li.username
        WHERE li.username = :username AND li.success = 0 ORDER BY li.login_date_time DESC
    """)
    fun findFailLoginByUsername(username: String):List<LoginInfo>

    @Query("""SELECT li.id,
        li.username,
        li.password,
        li.success,
        li.login_date_time
        FROM users u INNER JOIN login_info li ON u.username = li.username
        WHERE li.username = :username AND li.success = 1
    """)
    fun findLastSuccessLoginByUsername(username: String):List<LoginInfo>

    @Query("""SELECT li.id,
        li.username,
        li.password,
        li.success,
        li.login_date_time
        FROM users u INNER JOIN login_info li ON u.username = li.username
        WHERE li.username = :username AND li.success = 0
    """)
    fun findLastFailLoginByUsername(username: String):List<LoginInfo>

    @Insert
    fun save(loginInfo: LoginInfo)
}