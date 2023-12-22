package org.csystem.android.app.payment.data.service

import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import org.csystem.android.app.payment.data.service.dto.LoginInfoDTO
import org.csystem.android.app.payment.data.service.dto.LoginInfoSaveDTO
import org.csystem.android.app.payment.data.service.dto.PaymentSaveDTO
import org.csystem.android.app.payment.data.service.dto.UserSaveDTO
import org.csystem.android.app.payment.data.service.mapper.ILoginInfoMapper
import org.csystem.android.app.payment.data.service.mapper.IPaymentMapper
import org.csystem.android.app.payment.data.service.mapper.IUserMapper
import org.csystem.android.app.payment.repository.dal.PaymentApplicationHelper
import javax.inject.Inject


class PaymentApplicationDataService @Inject constructor(
    paymentApplicationHelper: PaymentApplicationHelper,
    userMapper: IUserMapper,
    loginInfoMapper: ILoginInfoMapper,
    paymentMapper: IPaymentMapper
)
{
    private val mPaymentApplicationHelper = paymentApplicationHelper
    private val mUserMapper = userMapper
    private val mLoginInfoMapper = loginInfoMapper
    private val mPaymentMapper = paymentMapper

    fun checkAndSaveLoginInfo(loginInfoSaveDTO: LoginInfoSaveDTO): Boolean
    {
        try
        { // Aşağıdaki kodlar DAL katmanına da eklenebilir
            if (!mPaymentApplicationHelper.existsUserByUsername(loginInfoSaveDTO.username))
                return false

            val loginInfo = mLoginInfoMapper.toLoginInfo(loginInfoSaveDTO)
            if (mPaymentApplicationHelper.existsUserByUsernameAndPassword(
                    loginInfo.username,
                    loginInfo.password
                )
            )
                mPaymentApplicationHelper.saveLoginInfo(loginInfo)
            else
                mPaymentApplicationHelper.saveLoginInfo(loginInfo.also {
                    it.success = false
                })

            return loginInfo.success
        } catch (ex: RepositoryException)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.checkAndSaveLoginInfo",
                ex.cause
            )
        } catch (ex: Throwable)
        {
            throw DataServiceException("PaymentApplicationDataService.checkAndSaveLoginInfo", ex)
        }
    }

    fun findLoginInfoByUsername(username: String): List<LoginInfoDTO>
    {
        try {
            return mPaymentApplicationHelper.findLoginInfoByUsername(username).map {
                mLoginInfoMapper.toLoginInfoDTO(it)
            }
        } catch (ex: RepositoryException)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.findLoginInfoByUsername",
                ex.cause
            )
        } catch (ex: Throwable)
        {
            throw DataServiceException("PaymentApplicationDataService.findLoginInfoByUsername", ex)
        }
    }

    fun findSuccessLoginInfoByUsername(username: String): List<LoginInfoDTO>
    {
        try
        {
            return mPaymentApplicationHelper.findSuccessLoginInfoByUsername(username).map {
                mLoginInfoMapper.toLoginInfoDTO(it)
            }
        } catch (ex: RepositoryException)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.findSuccessLoginInfoByUsername",
                ex.cause
            )
        } catch (ex: Throwable)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.findSuccessLoginInfoByUsername",
                ex
            )
        }
    }

    fun findFailLoginInfoByUsername(username: String): List<LoginInfoDTO>
    {
        try
        {
            return mPaymentApplicationHelper.findFailLoginInfoByUsername(username).map {
                mLoginInfoMapper.toLoginInfoDTO(it)
            }
        } catch (ex: RepositoryException)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.findFailLoginInfoByUsername",
                ex.cause
            )
        } catch (ex: Throwable)
        {
            throw DataServiceException(
                "PaymentApplicationDataService.findFailLoginInfoByUsername",
                ex
            )
        }
    }

    fun saveUser(userSaveDTO: UserSaveDTO): Boolean
    {
        var result = false
        try
        {
            mPaymentApplicationHelper.saveUser(mUserMapper.toUser(userSaveDTO))
            result = true
        } catch (ex: RepositoryException)
        {
            throw DataServiceException("PaymentApplicationDataService.saveUser", ex.cause)
        } catch (ex: Throwable)
        {
            throw DataServiceException("PaymentApplicationDataService.saveUser", ex)
        }
        return result
    }

    fun savePayment(paymentSaveDTO: PaymentSaveDTO)
    {
        try
        {
            mPaymentApplicationHelper.savePayment(mPaymentMapper.toPayment(paymentSaveDTO))
        } catch (ex: RepositoryException)
        {
            throw DataServiceException("PaymentApplicationDataService.savePayment", ex.cause)
        } catch (ex: Throwable)
        {
            throw DataServiceException("PaymentApplicationDataService.savePayment", ex)
        }
    }
}