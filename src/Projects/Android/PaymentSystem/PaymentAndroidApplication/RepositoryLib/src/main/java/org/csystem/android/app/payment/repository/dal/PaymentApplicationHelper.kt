package org.csystem.android.app.payment.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.csystem.android.app.payment.repository.dao.ILoginInfoRepository
import org.csystem.android.app.payment.repository.dao.IPaymentRepository
import org.csystem.android.app.payment.repository.dao.IUserRepository
import org.csystem.android.app.payment.repository.entity.LoginInfo
import org.csystem.android.app.payment.repository.entity.User
import java.io.IOException
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userRepository: IUserRepository

    @Inject
    lateinit var loginInfoRepository: ILoginInfoRepository

    @Inject
    lateinit var paymentRepository: IPaymentRepository

    fun saveUser(user:User):User?
    {
        try {
            return userRepository.save(user)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }

    fun findUserByUsernameAndPassword(username:String, password:String):User?
    {
        try {
            return userRepository.findByUsernameAndPassword(username, password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUsernameAndPassword", ex)
        }
    }

    fun existsUserByUsername(username: String?):Boolean
    {
        try {
            return userRepository.existsById(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUsername", ex)
        }
    }

    fun existsUserByUsernameAndPassword(username: String, password: String):Boolean
    {
        try {
            return userRepository.existsByUsernameAndPassword(username, password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUsernameAndPassword", ex)
        }
    }

    fun findLoginInfoByUsername(username:String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUsername", ex)
        }
    }

    fun findSuccessLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findSuccessLoginsByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUsername", ex)
        }
    }

    fun findFailLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findFailLoginsByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findFailLoginInfoByUsername", ex)
        }
    }

    fun saveLoginInfo(loginInfo: LoginInfo):LoginInfo
    {
        try {
            return loginInfoRepository.save(loginInfo)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo",ex)
        }
    }
}