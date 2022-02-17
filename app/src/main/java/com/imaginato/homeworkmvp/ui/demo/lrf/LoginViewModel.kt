package com.imaginato.homeworkmvp.ui.demo.lrf

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvp.common.extensions.setError
import com.imaginato.homeworkmvp.common.extensions.setLoading
import com.imaginato.homeworkmvp.common.extensions.setSuccess
import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserEntity
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel
import com.imaginato.homeworkmvp.data.lrf.usecase.InsertLoginDataUserCase
import com.imaginato.homeworkmvp.data.lrf.usecase.LoginUseCase
import com.imaginato.homeworkmvp.domain.base.UiState
import com.imaginato.homeworkmvp.ui.demo.common.BaseViewModel
import com.imaginato.homeworkmvp.ui.demo.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

const val PASSWORD_PATTERN = "^[a-zA-Z0-9 ]*$"


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val insertLoginDataUserCase: InsertLoginDataUserCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _loginValidationEvent = SingleLiveEvent<LoginValidation>()
    val loginValidationEvent: LiveData<LoginValidation> = _loginValidationEvent

    private val _loginLiveEvent: SingleLiveEvent<UiState<LoginUserResponse>> = SingleLiveEvent()
    val loginLiveEvent: LiveData<UiState<LoginUserResponse>> = _loginLiveEvent



    fun login(userRequestModel: UserRequestModel) {
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        print("pattern.matcher(userRequestModel.password).matches() ${pattern.matcher(userRequestModel.password).matches()}" )
        when {
            userRequestModel.username.isEmpty() -> {
                _loginValidationEvent.value = LoginValidation.USERNAME_BLANK
            }
            userRequestModel.password.isEmpty() -> {
                _loginValidationEvent.value = LoginValidation.PASSWORD_BLANK
            }
            !pattern.matcher(userRequestModel.password).matches() || userRequestModel.password.length > 16 -> {
                _loginValidationEvent.value = LoginValidation.PASSWORD_NOT_VALID
            }
            else -> {
                _loginValidationEvent.value = LoginValidation.SUCCESS
                _loginLiveEvent.setLoading()
                loginUseCase.invoke(
                    scope = viewModelScope,
                    params = LoginUseCase.Params(userRequestModel)
                ) {
                    it.result({ loginUserResponse ->
                        _loginLiveEvent.setSuccess(data = loginUserResponse)
                        // Save in Room DB
                        saveUserInLocal(userRequestModel)
                    }, { throwable ->
                        _loginLiveEvent.setError(throwable = throwable)
                    })
                }
            }
        }
    }

    private fun saveUserInLocal(userRequestModel: UserRequestModel) {
        val userEntity = UserEntity().apply {
            username = userRequestModel.username
            password = userRequestModel.password
        }
        insertLoginDataUserCase.invoke(
            scope = viewModelScope,
            params = InsertLoginDataUserCase.Params(userEntity)
        ) {
            it.result({}, { throwable ->
                Timber.d("Saved successfully")
            })
        }
    }
}