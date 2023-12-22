package org.csystem.android.app.payment.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.csystem.android.app.payment.repository.database.PaymentApplicationDatabase
import org.csystem.android.app.payment.repository.entity.Payment
import org.csystem.android.app.payment.repository.entity.User
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.time.LocalDate
import java.time.Month

@RunWith(AndroidJUnit4::class)
class PaymentRepositoryInstrumentedTest
{
    companion object
    {
        private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        private val database = Room.databaseBuilder(
            appContext,
            PaymentApplicationDatabase::class.java,
            "paymentdb-test.sqlite3"
        ).build()
        private val userDao = database.createUserDao()
        private val paymentDao = database.createPaymentDao()
    }



    private fun setUpUsers()
    {
        User("alican",
            "alican1234",
            "Alican",
            "Keçici",
            LocalDate.of(1989, Month.JANUARY, 5),
            LocalDate.now()
        ).apply { userDao.save(this) }

        User(
            "umut",
            "umut123",
            "Umut",
            "Utku",
            "Kırmızıgül",
            LocalDate.of(1995, Month.OCTOBER, 12),
            LocalDate.now()
        ).apply { userDao.save(this) }
    }

    private fun setUpPayments()
    {
        Payment(1L, "alican", 21.2, 10.0, "test1").apply { paymentDao.save(this) }
        Payment(2L, "alican", 13.4, 13.0, "test2").apply { paymentDao.save(this) }
        Payment(3L, "umut", 13.7, 4.7, "test3").apply { paymentDao.save(this) }
        Payment(4L, "umut", 13.7, 4.5, "test4").apply { paymentDao.save(this) }
        Payment(5L, "umut", 13.7, 4.4, "test5").apply { paymentDao.save(this) }
    }

    private fun deleteFiles()
    {
        val files = File(appContext.dataDir, "databases").listFiles()

        if (files != null)
            for (file in files)
                file.delete()
    }

    @Before
    fun setUp()
    {
        deleteFiles()
        setUpUsers()
        setUpPayments()
    }

    @Test
    fun test()
    {
        assertEquals(2, paymentDao.findByUserName("alican").size)
    }
}