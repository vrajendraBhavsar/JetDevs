package com.imaginato.homeworkmvp.data.local.demo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.data.lrf.repository.UserDao

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class DemoDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}