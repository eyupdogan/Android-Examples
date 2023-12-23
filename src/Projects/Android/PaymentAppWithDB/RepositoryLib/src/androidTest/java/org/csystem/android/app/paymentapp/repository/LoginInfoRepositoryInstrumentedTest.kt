package org.csystem.android.app.paymentapp.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.csystem.android.app.paymentapp.repository.dao.ILoginInfoDao
import org.csystem.android.app.paymentapp.repository.dao.IUserDao
import org.csystem.android.app.paymentapp.repository.database.PaymentApplicationDatabase
import org.csystem.android.app.paymentapp.repository.entity.LoginInfo
import org.csystem.android.app.paymentapp.repository.entity.User
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.time.LocalDate
import java.time.Month

@RunWith(AndroidJUnit4::class)
class LoginInfoRepositoryInstrumentedTest
{
    companion object {
        private val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    private var database:PaymentApplicationDatabase? = null
    private lateinit var loginInfoDao:ILoginInfoDao
    private lateinit var userDao: IUserDao

    private fun createDatabaseAndDao()
    {
        database = Room.databaseBuilder(appContext, PaymentApplicationDatabase::class.java, "paymentdb.sqlite3").build()
        loginInfoDao = database!!.createLoginInfoDao()
        userDao = database!!.createUserDao()
    }

    private fun deleteDatabaseFiles()
    {
        database?.close()

        createDatabaseAndDao()

        with(File(appContext.dataDir, "databases").listFiles()){
            if (this != null)
                for (file in this)
                    file.delete()
        }
    }

    private fun saveUsers()
    {
        User(
            "alican",
            "alican1234",
            "Alican",
            "Keçici",
            LocalDate.of(1989, Month.JANUARY, 5),
            LocalDate.now()
        ).apply {
            userDao.save(this)
        }
        User(
            "umut",
            "umut123",
            "Umut",
            "Utku",
            "Kırmızıgül",
            LocalDate.of(1995, Month.OCTOBER, 12),
            LocalDate.now()
        ).apply {
            userDao.save(this)
        }
    }

    private fun saveLoginInfo()
    {
        LoginInfo(1, "alican", success = true).apply { loginInfoDao.save(this) }

        LoginInfo(2, "umut", success = true).apply { loginInfoDao.save(this) }

        LoginInfo(3, "umut", success = false).apply { loginInfoDao.save(this) }
    }

    private fun saveData()
    {
        saveUsers()
        saveLoginInfo()
    }


    private fun initialize()
    {
        deleteDatabaseFiles()
        saveData()
    }

    @Test
    fun findByUserNameAndSuccessLoginCountTest()
    {
        initialize()
        Assert.assertEquals(1, loginInfoDao.findSuccessLoginByUsername("alican").size)
    }

    @Test
    fun findByUserNameAndFailLoginCountTest()
    {
        initialize()
        Assert.assertEquals(1, loginInfoDao.findFailLoginByUsername("umut").size)
    }

    @Test
    fun findByUserName_UsernameTrueTest()
    {
        initialize()
        Assert.assertEquals(2, loginInfoDao.findByUsername("umut").size)
    }

    @Test
    fun findByUserName_UserNameFalseTest()
    {
        initialize()
        Assert.assertEquals(0, loginInfoDao.findByUsername("baturhan").size)
    }
}