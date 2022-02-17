package com.imaginato.homeworkmvp.data.lrf.repository

import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginUserApi {

    @POST("api/login")
    suspend fun loginUser(@Body userRequestModel: UserRequestModel): LoginUserResponse
}