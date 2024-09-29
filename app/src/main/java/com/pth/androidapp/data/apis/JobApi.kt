package com.pth.androidapp.data.apis

import com.pth.androidapp.base.network.BaseResponse
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.data.models.job.JobInHome1
import com.pth.androidapp.data.models.job.JobInHome2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApi {
    @GET("jobs/new-jobs")
    suspend fun getNewJob(
        @Query("page") page: Number,
        @Query("size") size: Number
    ): Response<BaseResponse<ListingsResponse<JobInHome2>>>

    @GET("jobs/top-jobs")
    suspend fun getTopJob(
        @Query("page") page: Number,
        @Query("size") size: Number
    ): Response<BaseResponse<ListingsResponse<JobInHome1>>>
}