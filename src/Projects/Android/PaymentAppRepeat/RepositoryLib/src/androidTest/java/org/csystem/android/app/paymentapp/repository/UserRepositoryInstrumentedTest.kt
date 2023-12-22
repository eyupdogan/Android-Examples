package org.csystem.android.app.paymentapp.repository

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.csystem.android.app.paymentapp.repository.entity.User
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
class UserRepositoryInstrumentedTest
{
    companion object{
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val userRepository = UserDao(appContext)
    }

    private fun setUpUser()
    {
        val file = File(appContext.filesDir, USER_FILE)
        file.delete()

        val user1 = User("alican", "1234", "Alican", "Keçici", LocalDate.of(1989, Month.JANUARY, 5), LocalDate.now())
        val user2 = User("umut", "1234", "Umut", "Utku", "Keçici", LocalDate.of(1995, Month.OCTOBER, 12), LocalDate.now())

        userRepository.save(user1)
        userRepository.save(user2)
    }

    @Before
    fun setUp()
    {
        setUpUser()
    }

    @Test
    fun save_and_findByUsernameAndPasswordSuccessTest()
    {
        assertNotNull(userRepository.findByUsernameAndPassword("umut", "1234"))
    }

    @Test
    fun save_and_findByUsernameAndPasswordUsernameFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("alican", "1233wqe4"))
    }

    @Test
    fun save_and_findByUsernameAndPasswordPasswordFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("aflican", "1234"))
    }

    @Test
    fun save_and_findByUsernameAndPasswordBothFailTest()
    {
        assertNull(userRepository.findByUsernameAndPassword("aflican", "12das34"))
    }

    @Test
    fun existByUsernameAndPasswordSuccessTest()
    {
        assertTrue(userRepository.existsByUsernameAndPassword("umut", "1234"))
    }

    @Test
    fun existByUsernameAndPasswordFailTest()
    {
        assertTrue(userRepository.existsByUsernameAndPassword("umut12", "1234"))
    }

    @Test
    fun existByUsernameAndPasswordUsernameFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umut12", "1234"))
    }

    @Test
    fun existByUsernameAndPasswordPasswordFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umut12", "1234"))
    }

    @Test
    fun existByUsernameAndPasswordBothFailTest()
    {
        assertFalse(userRepository.existsByUsernameAndPassword("umut12", "1234"))
    }
}