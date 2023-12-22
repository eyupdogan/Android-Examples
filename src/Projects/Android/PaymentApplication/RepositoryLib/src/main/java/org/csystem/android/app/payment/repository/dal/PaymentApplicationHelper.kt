package org.csystem.android.app.payment.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.csystem.android.app.payment.repository.dao.ILoginInfoDao
import org.csystem.android.app.payment.repository.dao.IPaymentDao
import org.csystem.android.app.payment.repository.dao.IUserDao
import org.csystem.android.app.payment.repository.entity.LoginInfo
import org.csystem.android.app.payment.repository.entity.Payment
import org.csystem.android.app.payment.repository.entity.User
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userRepository: IUserDao

    @Inject
    lateinit var loginInfoRepository: ILoginInfoDao

    @Inject
    lateinit var paymentRepository: IPaymentDao

    fun existsUserByUsername(username: String): Boolean
    {
        try {
            return userRepository.existsById(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUsername", ex)
        }
    }

    fun existsUserByUsernameAndPassword(username: String, password: String): Boolean
    {
        try {
            return userRepository.existsByUserNameAndPassword(username, password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUsernameAndPassword", ex)
        }
    }

    fun saveUser(user:User)
    {
        try {
            return userRepository.save(user)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }

    fun findUserByUsernameAndPassword(username: String, password: String): User?
    {
        try {
            return userRepository.findByUserNameAndPassword(username, password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUsernameAndPassword", ex)
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

    fun findSuccessLoginInfoByUsername(username:String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findSuccessByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUsername", ex)
        }
    }

    fun findFailLoginInfoByUsername(username:String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findFailsByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findFailLoginInfoByUsername", ex)
        }
    }

    fun saveLoginInfo(loginInfo: LoginInfo)
    {
        try {
            return loginInfoRepository.save(loginInfo)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo", ex)
        }
    }

    fun savePayment(payment:Payment)
    {
        try {
            return paymentRepository.save(payment)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.savePayment", ex)
        }
    }

    /*
 fun findPaymentsByUserName(userName: String): List<User>
 {
     try {
         return paymentDao.findByUserName(userName)
     }
     catch (ex: Throwable) {
         throw RepositoryException("PaymentApplicationHelper.findPaymentsByUserName", ex)
     }
 }

  */
}