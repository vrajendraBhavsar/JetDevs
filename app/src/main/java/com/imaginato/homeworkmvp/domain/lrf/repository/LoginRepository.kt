package com.imaginato.homeworkmvp.domain.lrf.repository

import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel

interface LoginRepository {
    suspend fun loginUser(userRequestModel: UserRequestModel): LoginUserResponse
}