package com.pth.androidapp.ui.forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pth.androidapp.data.models.EmptyResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.base.state.TextFieldState
import com.pth.androidapp.base.viewmodels.BaseViewModel
import com.pth.androidapp.common.Utils
import com.pth.androidapp.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    val email = MutableLiveData(TextFieldState(text = ""))

    private val _sendForgotPasswordEmailResult =
        MutableLiveData<NetworkResult<EmptyResponse>>(NetworkResult.Idle)
    val sendForgotPasswordEmailResult: LiveData<NetworkResult<EmptyResponse>> =
        _sendForgotPasswordEmailResult

    fun sendForgotPasswordEmail() {
        viewModelScope.launch {
            val emailText = email.value?.text?.trim() ?: ""
            email.value = email.value?.copy(error = Utils.getEmailErrorMessage(emailText))

            if (email.value?.error == null ) {
                authRepository.forgotPassword(email.value!!.text).collect {
                    _sendForgotPasswordEmailResult.value = it
                }
            }
        }
    }
}