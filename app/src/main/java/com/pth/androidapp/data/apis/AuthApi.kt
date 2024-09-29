package com.pth.androidapp.data.apis

import com.pth.androidapp.base.network.BaseResponse
import com.pth.androidapp.data.models.EmptyResponse
import com.pth.androidapp.data.models.login.LoginRequest
import com.pth.androidapp.data.models.login.LoginResponse
import com.pth.androidapp.data.models.register.RegisterRequest
import com.pth.androidapp.data.models.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("auths/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<BaseResponse<LoginResponse>>

    @POST("auths/registerCandidate")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<BaseResponse<RegisterResponse>>

    @GET("auths/forgotPassword")
    suspend fun forgotPassword(
        @Query("email") email: String
    ): Response<BaseResponse<EmptyResponse>>
}