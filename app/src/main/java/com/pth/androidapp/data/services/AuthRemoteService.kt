package com.pth.androidapp.data.services

import com.pth.androidapp.base.network.BaseRemoteService
import com.pth.androidapp.data.models.EmptyResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.apis.AuthApi
import com.pth.androidapp.data.models.login.LoginRequest
import com.pth.androidapp.data.models.login.LoginResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRemoteService @Inject constructor(
    private val authApi: AuthApi
) : BaseRemoteService() {
    suspend fun login(loginRequest: LoginRequest): Flow<NetworkResult<LoginResponse>> =
        apiRequestFlow {
            authApi.login(loginRequest = loginRequest)
        }

    suspend fun forgotPassword(email: String): Flow<NetworkResult<EmptyResponse>> = apiRequestFlow {
        authApi.forgotPassword(email)
    }
}