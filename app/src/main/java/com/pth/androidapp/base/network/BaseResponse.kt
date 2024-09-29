package com.pth.androidapp.base.network

data class BaseResponse<T>(
    val Message: String,
    val Status: Int,
    val Data: T? = null
)

