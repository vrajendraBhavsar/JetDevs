package com.imaginato.homeworkmvp.domain.lrf.repository

import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity

interface DatabaseRepository {
    suspend fun insertData(userEntity: UserEntity)
}