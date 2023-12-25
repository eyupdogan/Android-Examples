package org.csystem.android.app.paymentapp.repository.dal

import com.karandev.util.data.repository.exception.RepositoryException
import org.csystem.android.app.paymentapp.repository.dao.ILoginInfoDao
import org.csystem.android.app.paymentapp.repository.dao.IPaymentDao
import org.csystem.android.app.paymentapp.repository.dao.IUserDao
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.User
import javax.inject.Inject

class PaymentApplicationHelper @Inject constructor()
{
    @Inject
    lateinit var userRepository: IUserDao

    @Inject
    lateinit var loginInfoRepository: ILoginInfoDao

    @Inject
    lateinit var paymentRepository: IPaymentDao

    fun existUserByUsername(username:String):Boolean
    {
        try {
            return userRepository.existsById(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.existUserByUsername", ex)
        }
    }

    fun existsUserByUsernameAndPassword(username:String, password:String):Boolean
    {
        try {
            return userRepository.existsByUsernameAndPassword(username,password)
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

    fun findUserByUsernameAndPassword(username:String, password:String):User?
    {
        try {
            return userRepository.findByUsernameAndPassword(username,password)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findUserByUsernameAndPassword", ex)
        }
    }

    fun findLoginInfoByUsername(username: String):List<LoginInfo>
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
            return loginInfoRepository.findSuccessLoginByUsername(username)
        }catch (ex:Throwable){
            throw RepositoryException("PaymentApplicationHelper.findSuccessLoginInfoByUsername", ex)
        }
    }

    fun findFailLoginInfoByUsername(username: String):List<LoginInfo>
    {
        try {
            return loginInfoRepository.findFailLoginByUsername(username)
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

//    fun findPaymentByUsername(username: String):List<UserToPayments>
//    {
//        try {
//            return paymentRepository.findByUsername(username)
//        }catch (ex:Throwable){
//            throw RepositoryException("PaymentApplicationHelper.findPaymentByUsername", ex)
//        }
//    }

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