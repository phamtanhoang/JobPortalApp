package com.pth.androidapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.base.state.TextFieldState
import com.pth.androidapp.base.viewmodels.BaseViewModel
import com.pth.androidapp.common.Utils
import com.pth.androidapp.data.models.login.LoginResponse
import com.pth.androidapp.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseViewModel() {
    val email = MutableLiveData(TextFieldState(text = "phamtanhoang3202@gmail.com"))
    val password = MutableLiveData(TextFieldState(text = "Hoang2002@"))

    val rememberMe = MutableLiveData(false)
    fun changeRememberMe() {
        rememberMe.value = rememberMe.value?.not()
    }

    private val _loginResult = MutableLiveData<NetworkResult<LoginResponse>>(NetworkResult.Idle)
    val loginResult: LiveData<NetworkResult<LoginResponse>> = _loginResult

    fun login() {
        viewModelScope.launch {
            val emailText = email.value?.text?.trim() ?: ""
            val passwordText = password.value?.text?.trim() ?: ""

            email.value = email.value?.copy(error = Utils.getEmailErrorMessage(emailText))
            password.value =
                password.value?.copy(error = Utils.getPasswordErrorMessage(passwordText))

            if (email.value?.error == null && password.value?.error == null) {
                authRepository.login(emailText, passwordText, "CANDIDATE").collect {
                    _loginResult.value = it
                }
            }
        }
    }
}