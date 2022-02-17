package com.imaginato.homeworkmvp.data.lrf.usecase

import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.domain.base.BaseUseCase
import com.imaginato.homeworkmvp.domain.lrf.repository.DatabaseRepository
import javax.inject.Inject


class InsertLoginDataUserCase @Inject constructor(private val databaseRepository: DatabaseRepository) :
    BaseUseCase<Unit, InsertLoginDataUserCase.Params>() {

    data class Params(
        var userEntity: UserEntity
    )

    override suspend fun execute(params: Params) {
        return databaseRepository.insertData(params.userEntity)
    }

}