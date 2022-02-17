package com.imaginato.homeworkmvp.ui.demo.lrf

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import com.imaginato.homeworkmvp.common.extensions.safeObserve
import com.imaginato.homeworkmvp.common.utils.ProgressDialogUtil
import com.imaginato.homeworkmvp.common.utils.ToastUtils
import com.imaginato.homeworkmvp.data.lrf.entity.LoginUserResponse
import com.imaginato.homeworkmvp.data.lrf.entity.UserRequestModel
import com.imaginato.homeworkmvp.databinding.FragmentLoginBinding
import com.imaginato.homeworkmvp.domain.base.UiState
import com.imaginato.homeworkmvp.ui.demo.common.BaseViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseViewBindingFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getClassName(): String = LoginFragment::class.java.simpleName

    override fun inflateLayout(layoutInflater: LayoutInflater): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        binding.apply {
            lifecycleOwner = this@LoginFragment
            viewModel = this@LoginFragment.viewModel
            handleListener()
        }
    }

    override fun initObservers() {
        super.initObservers()
        with(viewModel) {
            loginValidationEvent.safeObserve(
                viewLifecycleOwner,
                ::handleLoginValidation
            )
            loginLiveEvent.safeObserve(
                viewLifecycleOwner,
                ::handleLoginResponse
            )
        }
    }

    private fun handleLoginValidation(loginValidation: LoginValidation) {
        binding.tilUsername.error = null
        binding.tilPassword.error = null
        when (loginValidation) {
            LoginValidation.USERNAME_BLANK -> {
                binding.tilUsername.error = "Please enter your username"
            }
            LoginValidation.PASSWORD_BLANK -> {
                binding.tilPassword.error = "Please enter your password"
            }
            LoginValidation.PASSWORD_NOT_VALID -> {
                binding.tilPassword.error = "Please enter valid password"
            }
            LoginValidation.SUCCESS -> {
                binding.tilUsername.error = null
                binding.tilPassword.error = null
            }
        }
    }

    private fun handleLoginResponse(uiState: UiState<LoginUserResponse>?) {
        when (uiState) {
            is UiState.Loading -> {
                ProgressDialogUtil.showProgressDialog(requireContext())
            }
            is UiState.Success -> {
                ProgressDialogUtil.hideProgressDialog()
                ToastUtils.show(
                    requireContext(),
                    "Success -> ${uiState.data.errorCode}: ${uiState.data.errorMessage}"
                )
            }
            is UiState.Error -> {
                ToastUtils.show(requireContext(), "Error -> ${uiState.throwable}")
                ProgressDialogUtil.hideProgressDialog()
            }
        }
    }

    private fun handleListener() {
        binding.btnLogin.setOnClickListener {
            val userName = binding.etUserName.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.login(userRequestModel = UserRequestModel(userName, password))
        }
    }

}