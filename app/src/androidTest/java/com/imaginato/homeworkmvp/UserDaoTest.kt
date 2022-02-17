package com.imaginato.homeworkmvp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.platform.app.InstrumentationRegistry
import com.imaginato.homeworkmvp.data.local.demo.DemoDatabase
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.data.lrf.repository.UserDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDaoTest {
    private lateinit var database: DemoDatabase
    private lateinit var userDao: UserDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, DemoDatabase::class.java).build()
        userDao = database.userDao()

    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testUserEntry() = runBlocking {
        val userEntity = UserEntity()
        userEntity.id = 4
        userEntity.username = "Vraj"
        userEntity.password = "45655665"
        userDao.insert(userEntity)
        assertThat(userDao.loadAll().size, equalTo(2))
    }
}