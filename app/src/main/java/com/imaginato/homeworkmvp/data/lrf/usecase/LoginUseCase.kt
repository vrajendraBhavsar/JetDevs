package com.imaginato.homeworkmvp.data.lrf.usecase

import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel
import com.imaginato.homeworkmvp.domain.base.BaseUseCase
import com.imaginato.homeworkmvp.domain.lrf.repository.LoginRepository
import javax.inject.Inject


class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) :
    BaseUseCase<LoginUserResponse, LoginUseCase.Params>() {

    data class Params(val userRequestModel: UserRequestModel)

    override suspend fun execute(params: Params): LoginUserResponse {
        return  loginRepository.loginUser(userRequestModel = params.userRequestModel)
    }
}