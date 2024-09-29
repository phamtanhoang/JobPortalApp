package com.pth.androidapp.data.repositories

import com.pth.androidapp.data.models.ListingsResponse
import com.pth.androidapp.base.network.NetworkResult
import com.pth.androidapp.data.models.job.JobInHome1
import com.pth.androidapp.data.models.job.JobInHome2
import kotlinx.coroutines.flow.Flow


interface JobRepository {
    suspend fun getNewJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome2>>>

    suspend fun getTopJob(
        page: Number,
        size: Number
    ): Flow<NetworkResult<ListingsResponse<JobInHome1>>>

}