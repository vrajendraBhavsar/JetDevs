package com.imaginato.homeworkmvp.di.module

import com.imaginato.homeworkmvp.data.lrf.repository.DatabaseRepositoryImpl
import com.imaginato.homeworkmvp.data.lrf.repository.LoginRepositoryImpl
import com.imaginato.homeworkmvp.domain.lrf.repository.DatabaseRepository
import com.imaginato.homeworkmvp.domain.lrf.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesLoginRepository(
        randomUserRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Singleton
    @Binds
    abstract fun providesDataBaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl
    ): DatabaseRepository
}