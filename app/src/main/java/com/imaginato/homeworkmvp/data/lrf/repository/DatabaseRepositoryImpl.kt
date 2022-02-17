package com.imaginato.homeworkmvp.data.lrf.repository

import com.imaginato.homeworkmvp.data.local.demo.DemoDatabase
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.domain.lrf.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private var loginDatabase: DemoDatabase) :
    DatabaseRepository {

    override suspend fun insertData(userEntity: UserEntity) {
        loginDatabase.userDao().insert(user = userEntity)
    }
}