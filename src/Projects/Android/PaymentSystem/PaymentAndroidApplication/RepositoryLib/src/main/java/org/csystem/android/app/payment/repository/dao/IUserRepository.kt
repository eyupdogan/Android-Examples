package org.csystem.android.app.payment.repository.dao

import com.karandev.util.data.repository.ICrudRepository
import org.csystem.android.app.payment.repository.entity.User

interface IUserRepository : ICrudRepository<User, String>
{
    fun findByUsernameAndPassword(username: String, password: String): User?
    fun existsByUsernameAndPassword(username: String, password: String): Boolean =
        findByUsernameAndPassword(username, password) != null
}