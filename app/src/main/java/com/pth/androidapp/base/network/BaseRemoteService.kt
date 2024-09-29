package com.pth.androidapp.base.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

open class BaseRemoteService {
    protected suspend fun <T : Any> apiRequestFlow(
        call: suspend () -> Response<BaseResponse<T>>
    ): Flow<NetworkResult<T>> {
        return flow {
            emit(NetworkResult.Loading)

            withTimeoutOrNull(20000L) {
                val response = call()
                try {
                    if (response.isSuccessful) {
                        response.body().let { res ->
                            if (res?.Status != 200)
                                emit(
                                    NetworkResult.Error(
                                        status = res?.Status,
                                        message = res?.Message
                                    )
                                )
                            else
                                emit(
                                    NetworkResult.Success(
                                        data = res?.Data,
                                        message = res?.Message,
                                        status = res?.Status
                                    )
                                )
                        }
                    } else {
                        emit(
                            NetworkResult.Error(
                                status = response.code(),
                                message = response.message()
                            )
                        )
                    }
                } catch (e: Exception) {
                    emit(
                        NetworkResult.Error(
                            status = 400,
                            message = e.message ?: "Something went wrong!"
                        )
                    )
                }
            } ?: emit(NetworkResult.Error(status = 408, message = "Timeout! Please try again."))
        }.flowOn(Dispatchers.IO)
    }
}