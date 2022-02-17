package com.imaginato.homeworkmvp.di.module

import com.imaginato.homeworkmvp.data.lrf.repository.LoginUserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun providesRandomUserApi(retrofit: Retrofit): LoginUserApi {
        return retrofit.create(LoginUserApi::class.java)
    }

}