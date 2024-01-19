package org.csystem.android.app.payment.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.csystem.android.app.payment.repository.dao.ILoginInfoRepository
import org.csystem.android.app.payment.repository.entity.LoginInfo
import org.csystem.android.app.payment.repository.global.LOGIN_INFO_FILE
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Optional
import javax.inject.Inject

class LoginInfoRepository @Inject constructor(@ApplicationContext var context: Context):ILoginInfoRepository
{
    private fun findByUsernameCallback(fis:FileInputStream, username: String):List<LoginInfo>
    {
        val list = ArrayList<LoginInfo>()
        try {
            while (true){
                val loginInfo = ObjectInputStream(fis).readObject() as LoginInfo
                if (loginInfo.username == username)
                    list.add(loginInfo)
            }
        }catch (ex:EOFException){

        }
        return list
    }

    private fun <S:LoginInfo?>saveCallback(fos:FileOutputStream, loginInfo: S):S
    {
        ObjectOutputStream(fos).writeObject(loginInfo)
        return loginInfo
    }
    override fun findByUsername(username: String): List<LoginInfo>
    {
        return context.openFileInput(LOGIN_INFO_FILE).use { findByUsernameCallback(it, username) }
    }

    override fun <S : LoginInfo?> save(loginInfo: S): S
    {
        return context.openFileOutput(LOGIN_INFO_FILE, Context.MODE_APPEND).use { saveCallback(it, loginInfo) }
    }

    ////////////////////////////////////////////////////////////
    override fun count(): Long
    {
        TODO("Not yet implemented")
    }

    override fun findLastSuccessLoginsByUsername(username: String): List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findLastFailLoginsByUsername(username: String): List<LoginInfo>
    {
        TODO("Not yet implemented")
    }



    override fun findSuccessLoginsByUsername(username: String): List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findFailLoginsByUsername(username: String): List<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity: LoginInfo?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<LoginInfo>?)
    {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun <S : LoginInfo?> saveAll(entities: MutableIterable<S>?): MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Long>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Long?)
    {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Long?): Boolean
    {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: MutableIterable<Long>?): MutableIterable<LoginInfo>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long?): Optional<LoginInfo>
    {
        TODO("Not yet implemented")
    }
}