package com.pth.androidapp.base.network


sealed class NetworkResult<out T: Any> {
    data object Idle : NetworkResult<Nothing>()
    data object Loading : NetworkResult<Nothing>()

    data class Success<out T : Any>(
        val status: Int? = null,
        val message: String? = null,
        val data: T? = null,
    ) : NetworkResult<T>()

    data class Error(
        val status: Int? = null,
        val message: String? = null,
    ) : NetworkResult<Nothing>()
}


