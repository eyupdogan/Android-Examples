/*--------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.payment.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.csystem.android.app.payment.repository.dao.IUserRepository
import org.csystem.android.app.payment.repository.entity.User
import org.csystem.android.app.payment.repository.global.USER_FILE
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Optional
import javax.inject.Inject

class UserRepository @Inject constructor(@ApplicationContext context: Context):IUserRepository
{
    private val mContext:Context = context
    private fun <S:User?> saveCallback(fos:FileOutputStream, user:S):S
    {
        ObjectOutputStream(fos).writeObject(user)
        return user
    }

    private fun findByUsernameAndPasswordCallback(fis:FileInputStream, username: String, password: String):User?
    {
        var user:User?
        try {
            while (true){
                user = ObjectInputStream(fis).readObject() as? User
                if (user?.username == username && user.password == password)
                    break
            }
        }catch (ignore:EOFException){
            user = null
        }
        return user
    }
    override fun <S : User?> save(user: S): S
    {
        return mContext.openFileOutput(USER_FILE, Context.MODE_APPEND).use { saveCallback(it, user) }
    }

    override fun findByUsernameAndPassword(username: String, password: String): User?
    {
        return mContext.openFileInput(USER_FILE).use { findByUsernameAndPasswordCallback(it, username, password) }
    }

    ////////////////////////////////////////////////////////////

    override fun existsByUsernameAndPassword(username: String, password: String): Boolean
    {
        TODO("Not implemented yet")
    }





    override fun count(): Long
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity: User?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<User>?)
    {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<User>
    {
        TODO("Not yet implemented")
    }



    override fun <S : User?> saveAll(entities: MutableIterable<S>?): MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<String>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String?)
    {
        TODO("Not yet implemented")
    }

    override fun existsById(id: String?): Boolean
    {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: MutableIterable<String>?): MutableIterable<User>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id: String?): Optional<User>
    {
        TODO("Not yet implemented")
    }
}