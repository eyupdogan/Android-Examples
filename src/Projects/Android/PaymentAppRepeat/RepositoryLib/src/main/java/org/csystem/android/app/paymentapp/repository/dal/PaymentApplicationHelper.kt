package org.csystem.android.app.paymentapp.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.csystem.android.app.paymentapp.repository.dao.ILoginInfoDao
import org.csystem.android.app.paymentapp.repository.dao.IPaymentDao
import org.csystem.android.app.paymentapp.repository.dao.IUserDao
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.User
import org.csystem.android.app.paymentapp.repository.entity.join.UserToLogins
import org.csystem.android.app.paymentapp.repository.entity.join.UserToPayments
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userDao: IUserDao

    @Inject
    lateinit var loginInfoDao: ILoginInfoDao

    @Inject
    lateinit var paymentRepository: IPaymentDao

    fun existUserByUsername(username:String):Boolean
    {
        try {
            return userDao.existsById(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existUserByUsername", ex)
        }
    }

    fun existsUserByUsernameAndPassword(username:String, password:String):Boolean
    {
        try {
            return userDao.existsByUsernameAndPassword(username,password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existsUserByUsernameAndPassword", ex)
        }
    }

    fun saveUser(user:User)
    {
        try {
            return userDao.save(user)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveUser", ex)
        }
    }

    fun findUserByUsernameAndPassword(username:String, password:String):User?
    {
        try {
            return userDao.findByUsernameAndPassword(username,password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUsernameAndPassword", ex)
        }
    }

    fun findLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoDao.findByUserName(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findLoginInfoByUsername", ex)
        }
    }

    fun findSuccessLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoDao.findSuccessByUserName(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUsername", ex)
        }
    }

    fun findFailLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoDao.findFailsByUserName(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findFailLoginInfoByUsername", ex)
        }
    }

    fun saveLoginInfo(loginInfo: LoginInfo)
    {
        try {
            return loginInfoDao.save(loginInfo)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.saveLoginInfo", ex)
        }
    }

    fun findPaymentByUsername(username: String):List<UserToPayments>
    {
        try {
            return paymentRepository.findByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findPaymentByUsername", ex)
        }
    }

    fun savePayment(payment: Payment)
    {
        try {
            return paymentRepository.save(payment)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.savePayment", ex)
        }
    }
    // ...
}