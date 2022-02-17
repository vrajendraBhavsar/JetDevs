package com.imaginato.homeworkmvp.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.imaginato.homeworkmvp.BuildConfig
import com.imaginato.homeworkmvp.data.local.demo.DemoDatabase
import com.imaginato.homeworkmvp.data.lrf.repository.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): DemoDatabase {
        return Room.databaseBuilder(
            context,
            DemoDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomUserDao(demoDb: DemoDatabase): UserDao {
        return demoDb.userDao()
    }
}