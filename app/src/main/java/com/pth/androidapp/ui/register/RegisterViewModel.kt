package com.pth.androidapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.base.state.TextFieldState
import com.pth.androidapp.base.viewmodels.BaseViewModel
import com.pth.androidapp.data.models.login.LoginResponse
import com.pth.androidapp.data.models.register.RegisterResponse
import com.pth.androidapp.data.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): BaseViewModel() {
    val firstName = MutableLiveData(TextFieldState())
    val lastName = MutableLiveData(TextFieldState())
    val sex = MutableLiveData(TextFieldState())
    val dateOfBirth = MutableLiveData(TextFieldState())
    val phone = MutableLiveData(TextFieldState())
    val email = MutableLiveData(TextFieldState())
    val password = MutableLiveData(TextFieldState())
    val confirmPassword = MutableLiveData(TextFieldState())
    val agreePolicy = MutableLiveData(false)
    fun changeAgreePolicy(){
        agreePolicy.value = agreePolicy.value?.not()
    }

    private val _registerResult = MutableLiveData<NetworkResult<RegisterResponse>>(NetworkResult.Idle)
    val registerResult: LiveData<NetworkResult<RegisterResponse>> = _registerResult

    fun register(){

    }
}