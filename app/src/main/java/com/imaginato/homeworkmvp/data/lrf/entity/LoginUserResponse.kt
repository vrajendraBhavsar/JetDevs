package com.imaginato.homeworkmvp.data.lrf.entity


import com.google.gson.annotations.SerializedName

data class LoginUserResponse(
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("user")
    val userResponseModel: UserResponseModel
)

data class UserResponseModel(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String
)
