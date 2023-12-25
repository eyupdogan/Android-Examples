package org.csystem.android.app.paymentapp.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.csystem.android.app.paymentapp.repository.database.PaymentApplicationDatabase
import org.csystem.android.app.paymentapp.repository.entity.Payment
import org.csystem.android.app.paymentapp.repository.entity.User
import org.csystem.android.app.paymentapp.repository.global.PAYMENT_FILE
import org.csystem.android.app.paymentapp.repository.global.USER_FILE

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.File
import java.time.LocalDate
import java.time.Month

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PaymentRepositoryInstrumentedTest
{
    companion object{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val dataBase = Room.databaseBuilder(appContext, PaymentApplicationDatabase::class.java, "paymentdb-test.sqlite3").build()
        val userDao = dataBase.createUserDao()
        val paymentDao = dataBase.createPaymentDao()
    }

    private fun setUpUser()
    {
        File(appContext.filesDir, USER_FILE).delete()

        val user1 = User("alican", "1234", "Alican", "Keçici", LocalDate.of(1989, Month.JANUARY, 5), LocalDate.now())
        val user2 = User("umut", "1234", "Umut", "Utku", "Keçici", LocalDate.of(1995, Month.OCTOBER, 12), LocalDate.now())

        userDao.save(user1)
        userDao.save(user2)
    }

    private fun setUpPayments()
    {
        File(appContext.filesDir, PAYMENT_FILE).delete()

        val payment1 = Payment(1L, "alican", 34.5, 5, "payment1")
        val payment2 = Payment(2L, "alican", 34.1, 25, "payment2")
        val payment3 = Payment(3L, "umut", 34.1, 25, "payment3")
        val payment4 = Payment(4L, "umut", 34.1, 25, "payment4")
        val payment5 = Payment(5L, "umut", 34.1, 25, "payment5")

        paymentDao.save(payment1)
        paymentDao.save(payment2)
        paymentDao.save(payment3)
        paymentDao.save(payment4)
        paymentDao.save(payment5)
    }

    @Before
    fun setUp()
    {
        setUpUser()
        setUpPayments()
    }

    @Test
    fun save_and_findByUsernameSizeTest()
    {
        assertEquals(2, paymentDao.findByUsername("alican").size)
    }


}