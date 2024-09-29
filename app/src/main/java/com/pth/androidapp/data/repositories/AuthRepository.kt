package com.pth.androidapp.data.repositories

import com.pth.androidapp.data.models.EmptyResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.models.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(
        username: String,
        password: String,
        role: String
    ): Flow<NetworkResult<LoginResponse>>

    suspend fun logout()

    suspend fun forgotPassword(email: String): Flow<NetworkResult<EmptyResponse>>
}