package org.csystem.android.app.payment.repository

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.csystem.android.app.payment.repository.entity.User
import org.csystem.android.app.payment.repository.global.USER_FILE
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File
import java.time.LocalDate
import java.time.Month

@RunWith(AndroidJUnit4::class)
//@Ignore("tested before")
class UserRepositoryInstrumentedTest
{
    companion object
    {
        val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val userRepository = UserRepository(appContext)
    }

    @Before
    fun setUp()
    {
        val file = File(appContext.filesDir, USER_FILE)
        file.delete()

        with(User(
            "alican",
            "1234",
            "alican",
            "keçici",
            LocalDate.of(1989, Month.JANUARY, 5),
            LocalDate.now()
        )){
            userRepository.save(this)
        }

        with(User(
            "umut",
            "1234",
            "umut",
            "utku",
            "kırmızıgül",
            LocalDate.of(1995, Month.OCTOBER, 12),
            LocalDate.now()
        )){
            userRepository.save(this)
        }

    }

    @Test
    fun save_and_findByUsernameAndPasswordSuccessTest()
    {
        assertNotNull(userRepository.findByUsernameAndPassword("umut", "1234"))
    }

    @Test
    fun save_and_findByUsernameAndPasswordFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("umutdsadsa", "12sda34"))
    }

    @Test
    fun save_and_findByUsernameFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("umutdsadsa", "1234"))
    }

    @Test
    fun save_and_findByPasswordFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("vadsa", "1234"))
    }

    @Test
    fun save_and_findByUsernameAndPasswordBothFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("vadsa", "12dsad34"))
    }
    @Test
    fun existsByUsernameAndPasswordSuccessTest()
    {
        assertTrue(userRepository.existsByUsernameAndPassword("umut", "1234"))
    }

    @Test
    fun existsByUsernameAndPasswordFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umut", "123w4"))
    }

    @Test
    fun existsByUsernameAndPasswordUsernameFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umutdasd", "1234"))
    }

    @Test
    fun existsByUsernameAndPasswordPasswordFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umut", "123sdas4"))
    }

    @Test
    fun existsByIdSuccessTest()
    {
        assertTrue(userRepository.existsById("umut"))
    }

    @Test
    fun existsByIdFailTest()
    {
        assertFalse(userRepository.existsById("umdasdasut"))
    }


}