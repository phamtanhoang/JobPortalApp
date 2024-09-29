package com.pth.androidapp.data.apis

import com.pth.androidapp.base.network.BaseResponse
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.data.models.employer.Employer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmployerApi {

    @GET("employers/topEmployer")
    suspend fun getTopEmployers(
        @Query("page") page: Number,
        @Query("size") size: Number
    ): Response<BaseResponse<ListingsResponse<Employer>>>

}