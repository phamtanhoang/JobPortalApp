package com.pth.androidapp.data.repositories.impl

import com.pth.androidapp.data.models.EmptyResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.common.TokenManager
import com.pth.androidapp.data.models.login.LoginRequest
import com.pth.androidapp.data.models.login.LoginResponse
import com.pth.androidapp.data.repositories.AuthRepository
import com.pth.androidapp.data.services.AuthRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteService: AuthRemoteService,
    private val tokenManager: TokenManager
) : AuthRepository {
    override suspend fun login(
        username: String,
        password: String,
        role: String
    ): Flow<NetworkResult<LoginResponse>> {
        val loginRequest = LoginRequest(username, password, role)

        return authRemoteService.login(loginRequest).map { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.data!!.tokens.accessToken.let { token ->
                        tokenManager.saveAccessToken(token)
                    }
                    result
                }

                else -> result
            }
        }
    }

    override suspend fun logout() {
        try {
            tokenManager.clearTokens()
        } catch (e: Exception) {
        }
    }

    override suspend fun forgotPassword(email: String): Flow<NetworkResult<EmptyResponse>> =
        authRemoteService.forgotPassword(email)
}