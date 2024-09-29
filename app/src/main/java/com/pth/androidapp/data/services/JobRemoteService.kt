package com.pth.androidapp.data.services

import com.pth.androidapp.base.network.BaseRemoteService
import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.apis.JobApi
import com.pth.androidapp.data.models.job.JobInHome1
import com.pth.androidapp.data.models.job.JobInHome2
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobRemoteService @Inject constructor(
    private val jobApi: JobApi
) : BaseRemoteService() {
    suspend fun getNewJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome2>>> = apiRequestFlow {
        jobApi.getNewJob(page, size)
    }

    suspend fun getTopJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome1>>> = apiRequestFlow {
        jobApi.getTopJob(page, size)
    }
}