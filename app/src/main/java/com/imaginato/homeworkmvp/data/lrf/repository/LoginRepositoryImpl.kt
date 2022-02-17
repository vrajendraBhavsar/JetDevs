package com.imaginato.homeworkmvp.data.lrf.repository

import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel
import com.imaginato.homeworkmvp.domain.lrf.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val loginUserApi: LoginUserApi
): LoginRepository {

    override suspend fun loginUser(userRequestModel: UserRequestModel): LoginUserResponse {
        return loginUserApi.loginUser(userRequestModel)
    }
}